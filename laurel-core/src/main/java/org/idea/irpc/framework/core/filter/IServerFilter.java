package top.panson.irpc.framework.core.filter;

import top.panson.irpc.framework.core.common.RpcInvocation;


/**
 * 服务端过滤器
 *
 * @Author linhao
 * @Date created in 7:57 下午 2022/1/29
 */
public interface IServerFilter extends IFilter {

    /**
     * 执行核心过滤逻辑
     *
     * @param rpcInvocation
     */
    void doFilter(RpcInvocation rpcInvocation);
}
