package top.panson.irpc.framework.core.router;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.registy.URL;

import java.util.List;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.*;

/**
 * 轮训策略
 *
 * @Author linhao
 * @Date created in 8:30 下午 2022/1/5
 */
public class RotateRouterImpl implements IRouter{


    @Override
    public void refreshRouterArr(Selector selector) {
        List<ChannelFutureWrapper> channelFutureWrappers = CONNECT_MAP.get(selector.getProviderServiceName());
        ChannelFutureWrapper[] arr = new ChannelFutureWrapper[channelFutureWrappers.size()];
        for (int i=0;i<channelFutureWrappers.size();i++) {
            arr[i]=channelFutureWrappers.get(i);
        }
        SERVICE_ROUTER_MAP.put(selector.getProviderServiceName(),arr);
    }

    @Override
    public ChannelFutureWrapper select(Selector selector) {
        return CHANNEL_FUTURE_POLLING_REF.getChannelFutureWrapper(selector);
    }

    @Override
    public void updateWeight(URL url) {

    }
}
