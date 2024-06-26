package top.panson.irpc.framework.core.proxy.javassist;

import top.panson.irpc.framework.core.client.RpcReferenceWrapper;
import top.panson.irpc.framework.core.proxy.ProxyFactory;


/**
 * @Author linhao
 * @Date created in 5:32 下午 2021/12/4
 */
public class JavassistProxyFactory implements ProxyFactory {

    public JavassistProxyFactory() {
    }

    @Override
    public <T> T getProxy(RpcReferenceWrapper rpcReferenceWrapper) throws Throwable {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                rpcReferenceWrapper.getAimClass(), new JavassistInvocationHandler(rpcReferenceWrapper));
    }
}
