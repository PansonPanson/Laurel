package top.panson.irpc.framework.core.filter.client;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.common.utils.CommonUtils;
import top.panson.irpc.framework.core.filter.IClientFilter;

import java.util.Iterator;
import java.util.List;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.RESP_MAP;

/**
 * 基于分组的过滤链路
 *
 * @Author linhao
 * @Date created in 2:20 下午 2022/1/29
 */
public class GroupFilterImpl implements IClientFilter {

    @Override
    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        String group = String.valueOf(rpcInvocation.getAttachments().get("group"));
        Iterator<ChannelFutureWrapper> channelFutureWrapperIterator = src.iterator();
        while (channelFutureWrapperIterator.hasNext()) {
            ChannelFutureWrapper channelFutureWrapper = channelFutureWrapperIterator.next();
            if (!channelFutureWrapper.getGroup().equals(group)) {
                channelFutureWrapperIterator.remove();
            }
        }
        if (CommonUtils.isEmptyList(src)) {
            rpcInvocation.setRetry(0);
            rpcInvocation.setE(new RuntimeException("no provider match for service " + rpcInvocation.getTargetServiceName() + " in group " + group));
            rpcInvocation.setResponse(null);
            //直接交给响应线程那边处理（响应线程在代理类内部的invoke函数中，那边会取出对应的uuid的值，然后判断）
            RESP_MAP.put(rpcInvocation.getUuid(), rpcInvocation);
            throw new RuntimeException("no provider match for service " + rpcInvocation.getTargetServiceName() + " in group " + group);
        }
    }
}
