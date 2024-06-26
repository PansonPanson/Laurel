package top.panson.irpc.framework.core.router;

import top.panson.irpc.framework.core.common.ChannelFutureWrapper;
import top.panson.irpc.framework.core.registy.URL;

/**
 * @Author linhao
 * @Date created in 8:08 下午 2022/1/5
 */
public interface IRouter {


    /**
     * 刷新路由数组
     *
     * @param selector
     */
    void refreshRouterArr(Selector selector);

    /**
     * 获取到请求到连接通道
     *
     * @return
     */
    ChannelFutureWrapper select(Selector selector);

    /**
     * 更新权重信息
     *
     * @param url
     */
    void updateWeight(URL url);
}
