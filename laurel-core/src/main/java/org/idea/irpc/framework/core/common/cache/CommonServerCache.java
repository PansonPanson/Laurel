package top.panson.irpc.framework.core.common.cache;

import io.netty.util.internal.ConcurrentSet;
import top.panson.irpc.framework.core.common.ServerServiceSemaphoreWrapper;
import top.panson.irpc.framework.core.common.config.ServerConfig;
import top.panson.irpc.framework.core.dispatcher.ServerChannelDispatcher;
import top.panson.irpc.framework.core.filter.server.ServerAfterFilterChain;
import top.panson.irpc.framework.core.filter.server.ServerBeforeFilterChain;
import top.panson.irpc.framework.core.registy.URL;
import top.panson.irpc.framework.core.registy.zookeeper.AbstractRegister;
import top.panson.irpc.framework.core.serialize.SerializeFactory;
import top.panson.irpc.framework.core.server.ServiceWrapper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author linhao
 * @Date created in 8:45 下午 2021/12/1
 */
public class CommonServerCache {

    public static final Map<String,Object> PROVIDER_CLASS_MAP = new ConcurrentHashMap<>();
    public static final Set<URL> PROVIDER_URL_SET = new ConcurrentSet<>();
    public static AbstractRegister REGISTRY_SERVICE;
    public static SerializeFactory SERVER_SERIALIZE_FACTORY;
    public static ServerConfig SERVER_CONFIG;
    public static ServerBeforeFilterChain SERVER_BEFORE_FILTER_CHAIN;
    public static ServerAfterFilterChain SERVER_AFTER_FILTER_CHAIN;
    public static final Map<String, ServiceWrapper> PROVIDER_SERVICE_WRAPPER_MAP = new ConcurrentHashMap<>();
    public static Boolean IS_STARTED = false;
    public static ServerChannelDispatcher SERVER_CHANNEL_DISPATCHER = new ServerChannelDispatcher();
    public static final Map<String, ServerServiceSemaphoreWrapper> SERVER_SERVICE_SEMAPHORE_MAP = new ConcurrentHashMap<>(64);
}
