package top.panson.irpc.framework.core.proxy;


import top.panson.irpc.framework.core.client.RpcReferenceWrapper;

public interface ProxyFactory {

    <T> T getProxy(RpcReferenceWrapper rpcReferenceWrapper) throws Throwable;
}