package top.panson.irpc.framework.core.common.event.listener;

import io.netty.channel.ChannelFuture;
import top.panson.irpc.framework.core.client.ConnectionHandler;
import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.event.IRpcUpdateEvent;
import top.panson.irpc.framework.core.common.event.data.URLChangeWrapper;
import top.panson.irpc.framework.core.common.utils.CommonUtils;
import top.panson.irpc.framework.core.registy.URL;
import top.panson.irpc.framework.core.registy.zookeeper.ProviderNodeInfo;
import top.panson.irpc.framework.core.router.Selector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.*;

/**
 * @Author linhao
 * @Date created in 10:35 下午 2021/12/18
 */
public class ServiceUpdateListener implements IRpcListener<IRpcUpdateEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUpdateListener.class);

    @Override
    public void callBack(Object t) {
        //获取到子节点的数据信息
        URLChangeWrapper urlChangeWrapper = (URLChangeWrapper) t;
        List<ChannelFutureWrapper> channelFutureWrappers = CONNECT_MAP.get(urlChangeWrapper.getServiceName());
        List<String> matchProviderUrl = urlChangeWrapper.getProviderUrl();
        Set<String> finalUrl = new HashSet<>();
        List<ChannelFutureWrapper> finalChannelFutureWrappers = new ArrayList<>();
        for (ChannelFutureWrapper channelFutureWrapper : channelFutureWrappers) {
            String oldServerAddress = channelFutureWrapper.getHost() + ":" + channelFutureWrapper.getPort();
            //如果老的url没有，说明已经被移除了
            if (!matchProviderUrl.contains(oldServerAddress)) {
                continue;
            } else {
                finalChannelFutureWrappers.add(channelFutureWrapper);
                finalUrl.add(oldServerAddress);
            }
        }
        //此时老的url已经被移除了，开始检查是否有新的url
        List<ChannelFutureWrapper> newChannelFutureWrapper = new ArrayList<>();
        for (String newProviderUrl : matchProviderUrl) {
            //新增的节点数据
            if (!finalUrl.contains(newProviderUrl)) {
                ChannelFutureWrapper channelFutureWrapper = new ChannelFutureWrapper();
                String host = newProviderUrl.split(":")[0];
                Integer port = Integer.valueOf(newProviderUrl.split(":")[1]);
                channelFutureWrapper.setPort(port);
                channelFutureWrapper.setHost(host);
                String urlStr = urlChangeWrapper.getNodeDataUrl().get(newProviderUrl);
                ProviderNodeInfo providerNodeInfo = URL.buildURLFromUrlStr(urlStr);
                channelFutureWrapper.setWeight(providerNodeInfo.getWeight());
                channelFutureWrapper.setGroup(providerNodeInfo.getGroup());
                ChannelFuture channelFuture = null;
                try {
                    channelFuture = ConnectionHandler.createChannelFuture(host, port);
                    LOGGER.debug("channelFuture reconnect,server is {}:{}",host,port);
                    channelFutureWrapper.setChannelFuture(channelFuture);
                    newChannelFutureWrapper.add(channelFutureWrapper);
                    finalUrl.add(newProviderUrl);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        finalChannelFutureWrappers.addAll(newChannelFutureWrapper);
        //最终更新服务在这里
        CONNECT_MAP.put(urlChangeWrapper.getServiceName(), finalChannelFutureWrappers);
        Selector selector = new Selector();
        selector.setProviderServiceName(urlChangeWrapper.getServiceName());
        IROUTER.refreshRouterArr(selector);
    }
}
