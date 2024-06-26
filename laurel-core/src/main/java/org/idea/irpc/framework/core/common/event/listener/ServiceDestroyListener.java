package top.panson.irpc.framework.core.common.event.listener;

import top.panson.irpc.framework.core.common.event.IRpcDestroyEvent;
import top.panson.irpc.framework.core.registy.URL;

import static top.panson.irpc.framework.core.common.cache.CommonServerCache.PROVIDER_URL_SET;
import static top.panson.irpc.framework.core.common.cache.CommonServerCache.REGISTRY_SERVICE;

/**
 * 服务注销 监听器
 *
 * @Author linhao
 * @Date created in 3:18 下午 2022/1/8
 */
public class ServiceDestroyListener implements IRpcListener<IRpcDestroyEvent> {

    @Override
    public void callBack(Object t) {
        for (URL url : PROVIDER_URL_SET) {
            REGISTRY_SERVICE.unRegister(url);
        }
    }
}
