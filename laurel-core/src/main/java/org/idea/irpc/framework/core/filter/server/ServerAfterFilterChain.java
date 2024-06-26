package top.panson.irpc.framework.core.filter.server;

import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.filter.IServerFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author linhao
 * @Date created in 8:40 下午 2022/1/29
 */
public class ServerAfterFilterChain {

    private static List<IServerFilter> iServerFilters = new ArrayList<>();

    public void addServerFilter(IServerFilter iServerFilter) {
        iServerFilters.add(iServerFilter);
    }

    public void doFilter(RpcInvocation rpcInvocation) {
        for (IServerFilter iServerFilter : iServerFilters) {
            iServerFilter.doFilter(rpcInvocation);
        }
    }
}
