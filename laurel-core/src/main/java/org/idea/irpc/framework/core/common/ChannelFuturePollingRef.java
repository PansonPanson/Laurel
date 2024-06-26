package top.panson.irpc.framework.core.common;

import top.panson.irpc.framework.core.router.Selector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @Author linhao
 * @Date created in 8:46 下午 2022/1/5
 */
public class ChannelFuturePollingRef {


    private Map<String,AtomicLong> referenceMap = new ConcurrentHashMap<>();

    public ChannelFutureWrapper getChannelFutureWrapper(Selector selector){
        AtomicLong referCount = referenceMap.get(selector.getProviderServiceName());
        if(referCount==null){
            referCount = new AtomicLong(0);
            referenceMap.put(selector.getProviderServiceName(),referCount);
        }
        ChannelFutureWrapper[] arr = selector.getChannelFutureWrappers();
        long i = referCount.getAndIncrement();
        int index = (int) (i % arr.length);
        return arr[index];
    }

}
