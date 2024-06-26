package top.panson.irpc.framework.core.common.event.listener;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.event.IRpcNodeChangeEvent;
import top.panson.irpc.framework.core.registy.URL;
import top.panson.irpc.framework.core.registy.zookeeper.ProviderNodeInfo;

import java.util.List;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.*;

/**
 * @Author linhao
 * @Date created in 8:47 下午 2022/1/16
 */
public class ProviderNodeDataChangeListener implements IRpcListener<IRpcNodeChangeEvent> {

    @Override
    public void callBack(Object t) {
        ProviderNodeInfo providerNodeInfo = ((ProviderNodeInfo) t);
        List<ChannelFutureWrapper> channelFutureWrappers =  CONNECT_MAP.get(providerNodeInfo.getServiceName());
        for (ChannelFutureWrapper channelFutureWrapper : channelFutureWrappers) {
            //重置分组信息
            String address = channelFutureWrapper.getHost()+":"+channelFutureWrapper.getPort();
            if(address.equals(providerNodeInfo.getAddress())){
                channelFutureWrapper.setGroup(providerNodeInfo.getGroup());
                //修改权重
                channelFutureWrapper.setWeight(providerNodeInfo.getWeight());
                URL url = new URL();
                url.setServiceName(providerNodeInfo.getServiceName());
                //更新权重
                IROUTER.updateWeight(url);
                break;
            }
        }
    }
}
