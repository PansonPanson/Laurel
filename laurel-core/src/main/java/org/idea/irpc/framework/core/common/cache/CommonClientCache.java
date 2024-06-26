package top.panson.irpc.framework.core.common.cache;

import top.panson.irpc.framework.core.common.ChannelFuturePollingRef;
import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.common.RpcInvocation;
import top.panson.irpc.framework.core.common.config.ClientConfig;
import top.panson.irpc.framework.core.filter.client.ClientFilterChain;
import top.panson.irpc.framework.core.registy.URL;
import top.panson.irpc.framework.core.registy.zookeeper.AbstractRegister;
import top.panson.irpc.framework.core.router.IRouter;
import top.panson.irpc.framework.core.serialize.SerializeFactory;
import top.panson.irpc.framework.core.spi.ExtensionLoader;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 公用缓存 存储请求队列等公共信息
 *
 * @Author linhao
 * @Date created in 8:44 下午 2021/12/1
 */
public class CommonClientCache {

    public static BlockingQueue<RpcInvocation> SEND_QUEUE = new ArrayBlockingQueue(5000);
    public static Map<String, Object> RESP_MAP = new ConcurrentHashMap<>();
    //provider名称 --> 该服务有哪些集群URL
    public static List<URL> SUBSCRIBE_SERVICE_LIST = new ArrayList<>();
    //com.sise.test.service -> <<ip:host,urlString>,<ip:host,urlString>,<ip:host,urlString>>
    public static Map<String, Map<String,String>> URL_MAP = new ConcurrentHashMap<>();
    public static Set<String> SERVER_ADDRESS = new HashSet<>();
    //每次进行远程调用的时候都是从这里面去选择服务提供者
    public static Map<String, List<ChannelFutureWrapper>> CONNECT_MAP = new ConcurrentHashMap<>();
    //随机请求的map
    public static Map<String, ChannelFutureWrapper[]> SERVICE_ROUTER_MAP = new ConcurrentHashMap<>();
    public static ChannelFuturePollingRef CHANNEL_FUTURE_POLLING_REF = new ChannelFuturePollingRef();
    public static IRouter IROUTER;
    public static SerializeFactory CLIENT_SERIALIZE_FACTORY;
    public static ClientConfig CLIENT_CONFIG;
    public static ClientFilterChain CLIENT_FILTER_CHAIN;
    public static AbstractRegister ABSTRACT_REGISTER;
    public static ExtensionLoader EXTENSION_LOADER = new ExtensionLoader();
    public static AtomicLong addReqTime = new AtomicLong(0);
}
