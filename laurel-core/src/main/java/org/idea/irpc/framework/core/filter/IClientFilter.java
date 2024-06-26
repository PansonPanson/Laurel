package top.panson.irpc.framework.core.filter;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.RpcInvocation;

import java.util.List;

/**
 * 客户端过滤器
 *
 * @Author linhao
 * @Date created in 7:56 下午 2022/1/29
 */
public interface IClientFilter extends IFilter {

    /**
     * 执行过滤链
     *
     * @param src
     * @param rpcInvocation
     * @return
     */
    void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation);
}
