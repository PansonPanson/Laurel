package top.panson.irpc.framework.jmh.proxy;

import top.panson.irpc.framework.core.client.Client;
import top.panson.irpc.framework.core.client.ConnectionHandler;
import top.panson.irpc.framework.core.client.RpcReference;
import top.panson.irpc.framework.core.client.RpcReferenceWrapper;
import top.panson.irpc.framework.interfaces.DataService;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Author linhao
 * @Date created in 5:16 下午 2021/12/18
 */
public class ProxyCompareTest {

    private static Client client;
    private static DataService dataService;
    private static RpcReference rpcReference;

    static {
        client = new Client();
        try {
            rpcReference = client.initClientApplication();
            RpcReferenceWrapper<DataService> rpcReferenceWrapper = new RpcReferenceWrapper();
            rpcReferenceWrapper.setAimClass(DataService.class);
            rpcReferenceWrapper.setGroup("default");
            dataService = rpcReference.get(rpcReferenceWrapper);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        client.doSubscribeService(DataService.class);
        ConnectionHandler.setBootstrap(client.getBootstrap());
        client.doConnectServer();
        client.startClient();
        System.out.println("初始化客户端程序");
    }


//    @Benchmark
    public String testJdkProxy() throws Throwable {
        String content = dataService.sendData("test");
        return content;
    }

    /**
     * 修改irpc.properties配置文件中的代理模式再重启客户端进行测试
     *
     * @return
     * @throws Throwable
     */
//    @Benchmark
//    public String testJavassistProxy() throws Throwable {
//        String content = dataService.sendData("test");
//        return content;
//    }


//    public static void main(String[] args) throws RunnerException {
//        //配置进行2轮热数 测试2轮 1个线程
//        //预热的原因 是JVM在代码执行多次会有优化
//        Options options = new OptionsBuilder().warmupIterations(2).measurementBatchSize(2)
//                .forks(1).build();
//        new Runner(options).run();
//    }
}
