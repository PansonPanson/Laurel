package top.panson.irpc.framework.consumer.spi.filter;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.filter.IClientFilter;

import java.util.List;

/**
 * @Author linhao
 * @Date created in 4:31 下午 2022/2/4
 */
public class LogFilterImpl implements IClientFilter {
    @Override
    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        System.out.println("this is a test");
    }
}
