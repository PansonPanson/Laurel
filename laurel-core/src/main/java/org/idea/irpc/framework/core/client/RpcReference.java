package top.panson.irpc.framework.core.client;

import top.panson.irpc.framework.core.proxy.ProxyFactory;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.CLIENT_CONFIG;

/**
 * @Author linhao
 * @Date created in 10:49 上午 2021/12/11
 */
public class RpcReference {

    public ProxyFactory proxyFactory;


    public RpcReference(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    /**
     * 根据接口类型获取代理对象
     *
     * @param rpcReferenceWrapper
     * @param <T>
     * @return
     */
    public <T> T get(RpcReferenceWrapper<T> rpcReferenceWrapper) throws Throwable {
        initGlobalRpcReferenceWrapperConfig(rpcReferenceWrapper);
        return proxyFactory.getProxy(rpcReferenceWrapper);
    }

    /**
     * 初始化远程调用的一些全局配置,例如超时
     *
     * @param rpcReferenceWrapper
     */
    private void initGlobalRpcReferenceWrapperConfig(RpcReferenceWrapper rpcReferenceWrapper) {
        if (rpcReferenceWrapper.getTimeOUt() == null) {
            rpcReferenceWrapper.setTimeOut(CLIENT_CONFIG.getTimeOut());
        }
    }
}
