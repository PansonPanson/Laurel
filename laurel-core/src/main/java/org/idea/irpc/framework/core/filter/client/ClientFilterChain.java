package top.panson.irpc.framework.core.filter.client;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.filter.IClientFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author linhao
 * @Date created in 8:18 下午 2022/1/29
 */
public class ClientFilterChain {

    private static List<IClientFilter> iClientFilterList = new ArrayList<>();

    public void addClientFilter(IClientFilter iClientFilter) {
        iClientFilterList.add(iClientFilter);
    }

    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        for (IClientFilter iClientFilter : iClientFilterList) {
            iClientFilter.doFilter(src, rpcInvocation);
        }
    }

}
