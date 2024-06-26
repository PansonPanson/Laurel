package top.panson.irpc.framework.core.common.event;

/**
 * @Author linhao
 * @Date created in 8:44 下午 2022/1/16
 */
public class IRpcNodeChangeEvent implements IRpcEvent {

    private Object data;

    public IRpcNodeChangeEvent(Object data) {
        this.data = data;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public IRpcEvent setData(Object data) {
        this.data = data;
        return this;
    }
}
