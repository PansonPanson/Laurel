package top.panson.irpc.framework.core.common.event.listener;

/**
 * @Author linhao
 * @Date created in 11:49 下午 2021/12/13
 */
public interface IRpcListener<T> {

    void callBack(Object t);

}
