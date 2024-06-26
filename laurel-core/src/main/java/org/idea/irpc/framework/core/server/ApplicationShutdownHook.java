package top.panson.irpc.framework.core.server;


import top.panson.irpc.framework.core.common.event.IRpcDestroyEvent;
import top.panson.irpc.framework.core.common.event.IRpcListenerLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IdentityHashMap;


/**
 * 监听java进程被关闭
 *
 * @Author linhao
 * @Date created in 9:11 下午 2021/12/19
 */
public class ApplicationShutdownHook {

    public static Logger LOGGER = LoggerFactory.getLogger(ApplicationShutdownHook.class);

    /**
     * 注册一个shutdownHook的钩子，当jvm进程关闭的时候触发
     */
    public static void registryShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("[registryShutdownHook] ==== ");
                IRpcListenerLoader.sendSyncEvent(new IRpcDestroyEvent("destroy"));
                System.out.println("destory");
            }
        }));
    }

}
