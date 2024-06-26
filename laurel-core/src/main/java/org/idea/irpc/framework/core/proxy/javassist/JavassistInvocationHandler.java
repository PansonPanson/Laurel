package top.panson.irpc.framework.core.proxy.javassist;

import top.panson.irpc.framework.core.client.RpcReferenceWrapper;
import top.panson.irpc.framework.core.common.RpcInvocation;
import sun.rmi.runtime.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.*;
import static top.panson.irpc.framework.core.common.constants.RpcConstants.DEFAULT_TIMEOUT;

/**
 * @Author linhao
 * @Date created in 7:15 下午 2021/12/5
 */
public class JavassistInvocationHandler implements InvocationHandler {


    private final static Object OBJECT = new Object();

    private RpcReferenceWrapper rpcReferenceWrapper;

    private Long timeOut = Long.valueOf(DEFAULT_TIMEOUT);

    public JavassistInvocationHandler(RpcReferenceWrapper rpcReferenceWrapper) {
        this.rpcReferenceWrapper = rpcReferenceWrapper;
        timeOut = Long.valueOf(String.valueOf(rpcReferenceWrapper.getAttatchments().get("timeOut")));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcInvocation rpcInvocation = new RpcInvocation();
        rpcInvocation.setArgs(args);
        rpcInvocation.setTargetMethod(method.getName());
        rpcInvocation.setTargetServiceName(rpcReferenceWrapper.getAimClass().getName());
        rpcInvocation.setAttachments(rpcReferenceWrapper.getAttatchments());
        rpcInvocation.setUuid(UUID.randomUUID().toString());
        rpcInvocation.setRetry(rpcReferenceWrapper.getRetry());
        SEND_QUEUE.add(rpcInvocation);
        if (rpcReferenceWrapper.isAsync()) {
            return null;
        }
        RESP_MAP.put(rpcInvocation.getUuid(), OBJECT);
        long beginTime = System.currentTimeMillis();
        int retryTimes = 0;
        while (System.currentTimeMillis() - beginTime < timeOut || rpcInvocation.getRetry() > 0) {
            Object object = RESP_MAP.get(rpcInvocation.getUuid());
            if (object != null && object instanceof RpcInvocation) {
                RpcInvocation rpcInvocationResp = (RpcInvocation) object;
                //正常结果
                if (rpcInvocationResp.getRetry() == 0 || (rpcInvocationResp.getRetry() != 0 && rpcInvocationResp.getE() == null)) {
                    RESP_MAP.remove(rpcInvocation.getUuid());
                    return rpcInvocationResp.getResponse();
                } else if (rpcInvocationResp.getE() != null) {
                    if (rpcInvocationResp.getRetry() == 0) {
                        RESP_MAP.remove(rpcInvocation.getUuid());
                        return rpcInvocationResp.getResponse();
                    }
                }
            }
            if (OBJECT.equals(object)) {
                //超时重试
                if (System.currentTimeMillis() - beginTime > timeOut) {
                    retryTimes++;
                    //重新请求
                    rpcInvocation.setResponse(null);
                    //每次重试之后都会将retry值扣减1
                    rpcInvocation.setRetry(rpcInvocation.getRetry() - 1);
                    RESP_MAP.put(rpcInvocation.getUuid(), OBJECT);
                    SEND_QUEUE.add(rpcInvocation);
                }
            }
        }
        //应对一些请求超时的情况
        RESP_MAP.remove(rpcInvocation.getUuid());
        throw new TimeoutException("Wait for response from server on client " + timeOut + "ms,retry times is " + retryTimes + ",service's name is " + rpcInvocation.getTargetServiceName() + "#" + rpcInvocation.getTargetMethod());
    }
}
