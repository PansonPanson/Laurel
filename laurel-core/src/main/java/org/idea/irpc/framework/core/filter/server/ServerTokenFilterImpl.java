package top.panson.irpc.framework.core.filter.server;

import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.common.annotations.SPI;
import top.panson.irpc.framework.core.common.exception.IRpcException;
import top.panson.irpc.framework.core.common.utils.CommonUtils;
import top.panson.irpc.framework.core.filter.IServerFilter;
import top.panson.irpc.framework.core.server.ServiceWrapper;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.RESP_MAP;
import static top.panson.irpc.framework.core.common.cache.CommonServerCache.PROVIDER_SERVICE_WRAPPER_MAP;

/**
 * 简单版本的token校验
 *
 * @Author linhao
 * @Date created in 9:04 上午 2022/2/1
 */
@SPI("before")
public class ServerTokenFilterImpl implements IServerFilter {

    @Override
    public void doFilter(RpcInvocation rpcInvocation) {
        String token = String.valueOf(rpcInvocation.getAttachments().get("serviceToken"));
        ServiceWrapper serviceWrapper = PROVIDER_SERVICE_WRAPPER_MAP.get(rpcInvocation.getTargetServiceName());
        String matchToken = String.valueOf(serviceWrapper.getServiceToken());
        if (CommonUtils.isEmpty(matchToken)) {
            return;
        }
        if (!CommonUtils.isEmpty(token) && token.equals(matchToken)) {
            return;
        } else {
            rpcInvocation.setRetry(0);
            rpcInvocation.setE(new RuntimeException("service token is illegal for service " + rpcInvocation.getTargetServiceName()));
            rpcInvocation.setResponse(null);
            //直接交给响应线程那边处理（响应线程在代理类内部的invoke函数中，那边会取出对应的uuid的值，然后判断）
            RESP_MAP.put(rpcInvocation.getUuid(), rpcInvocation);
            throw new IRpcException(rpcInvocation);
        }
    }
}
