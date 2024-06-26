package top.panson.irpc.framework.core.registy;

/**
 * @Author linhao
 * @Date created in 11:26 上午 2021/12/11
 */
public interface RegistryService {


    /**
     * 注册url
     *
     * 将irpc服务写入注册中心节点
     * 当出现网络抖动的时候需要进行适当的重试做法
     * 注册服务url的时候需要写入持久化文件中
     *
     * @param url
     */
    void register(URL url);

    /**
     * 服务下线
     *
     * 持久化节点是无法进行服务下线操作的
     * 下线的服务必须保证url是完整匹配的
     * 移除持久化文件中的一些内容信息
     *
     * @param url
     */
    void unRegister(URL url);

    /**
     * 消费方订阅服务
     *
     * @param url
     */
    void subscribe(URL url);


    /**
     * 执行取消订阅内部的逻辑
     *
     * @param url
     */
    void doUnSubscribe(URL url);
}
