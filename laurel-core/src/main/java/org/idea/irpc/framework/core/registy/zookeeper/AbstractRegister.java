package top.panson.irpc.framework.core.registy.zookeeper;

import top.panson.irpc.framework.core.registy.RegistryService;
import top.panson.irpc.framework.core.registy.URL;

import java.util.List;
import java.util.Map;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.SUBSCRIBE_SERVICE_LIST;
import static top.panson.irpc.framework.core.common.cache.CommonServerCache.PROVIDER_URL_SET;

/**
 * @Author linhao
 * @Date created in 3:57 下午 2021/12/11
 */
public abstract class AbstractRegister implements RegistryService {


    @Override
    public void register(URL url) {
        PROVIDER_URL_SET.add(url);
    }

    @Override
    public void unRegister(URL url) {
        PROVIDER_URL_SET.remove(url);
    }

    @Override
    public void subscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.add(url);
    }

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doAfterSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doBeforeSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param serviceName
     * @return
     */
    public abstract List<String> getProviderIps(String serviceName);

    /**
     * 获取服务的权重信息
     *
     * @param serviceName
     * @return <ip:port --> urlString>,<ip:port --> urlString>,<ip:port --> urlString>,<ip:port --> urlString>
     */
    public abstract Map<String, String> getServiceWeightMap(String serviceName);

    @Override
    public void doUnSubscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.remove(url.getServiceName());
    }
}
