package top.panson.irpc.framework.core.router;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;

/**
 * @Author linhao
 * @Date created in 8:13 下午 2022/1/5
 */
public class Selector {

    /**
     * 服务命名
     * eg: com.sise.test.DataService
     */
    private String providerServiceName;

    /**
     * 经过二次筛选之后的future集合
     */
    private ChannelFutureWrapper[] channelFutureWrappers;

    public String getProviderServiceName() {
        return providerServiceName;
    }

    public void setProviderServiceName(String providerServiceName) {
        this.providerServiceName = providerServiceName;
    }

    public ChannelFutureWrapper[] getChannelFutureWrappers() {
        return channelFutureWrappers;
    }

    public void setChannelFutureWrappers(ChannelFutureWrapper[] channelFutureWrappers) {
        this.channelFutureWrappers = channelFutureWrappers;
    }
}
