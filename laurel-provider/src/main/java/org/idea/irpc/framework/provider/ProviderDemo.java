package top.panson.irpc.framework.provider;

import java.io.IOException;

import top.panson.irpc.framework.core.common.event.IRpcListenerLoader;
import top.panson.irpc.framework.core.server.*;

/**
 * @Author linhao
 * @Date created in 4:25 下午 2022/2/4
 */
public class ProviderDemo {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        Server server = new Server();
        server.initServerConfig();
        IRpcListenerLoader iRpcListenerLoader = new IRpcListenerLoader();
        iRpcListenerLoader.init();
        ServiceWrapper dataServiceServiceWrapper = new ServiceWrapper(new DataServiceImpl(), "dev");
        dataServiceServiceWrapper.setServiceToken("token-a");
        dataServiceServiceWrapper.setLimit(4);
        ServiceWrapper userServiceServiceWrapper = new ServiceWrapper(new UserServiceImpl(), "dev");
        userServiceServiceWrapper.setServiceToken("token-b");
        userServiceServiceWrapper.setLimit(4);
        server.exportService(dataServiceServiceWrapper);
        server.exportService(userServiceServiceWrapper);
        ApplicationShutdownHook.registryShutdownHook();
        server.startApplication();
    }
}
