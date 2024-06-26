package top.panson.irpc.framework.core.filter.client;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.common.utils.CommonUtils;
import top.panson.irpc.framework.core.filter.IClientFilter;

import java.util.Iterator;
import java.util.List;

import static top.panson.irpc.framework.core.common.cache.CommonClientCache.RESP_MAP;

/**
 * 直连过滤器
 *
 * @Author linhao
 * @Date created in 9:04 上午 2022/2/1
 */
public class DirectInvokeFilterImpl implements IClientFilter {

    @Override
    public void doFilter(List<ChannelFutureWrapper> src, RpcInvocation rpcInvocation) {
        String url = (String) rpcInvocation.getAttachments().get("url");
        if(CommonUtils.isEmpty(url)){
            return;
        }
        Iterator<ChannelFutureWrapper> channelFutureWrapperIterator = src.iterator();
        while (channelFutureWrapperIterator.hasNext()){
            ChannelFutureWrapper channelFutureWrapper = channelFutureWrapperIterator.next();
            if(!(channelFutureWrapper.getHost()+":"+channelFutureWrapper.getPort()).equals(url)){
                channelFutureWrapperIterator.remove();
            }
        }
        if(CommonUtils.isEmptyList(src)){
            rpcInvocation.setRetry(0);
            rpcInvocation.setE(new RuntimeException("no provider match for service " + rpcInvocation.getTargetServiceName()  + " in url " + url));
            rpcInvocation.setResponse(null);
            //直接交给响应线程那边处理（响应线程在代理类内部的invoke函数中，那边会取出对应的uuid的值，然后判断）
            RESP_MAP.put(rpcInvocation.getUuid(), rpcInvocation);
            throw new RuntimeException("no provider match for service " + rpcInvocation.getTargetServiceName() + " in url " + url);
        }
    }
}
