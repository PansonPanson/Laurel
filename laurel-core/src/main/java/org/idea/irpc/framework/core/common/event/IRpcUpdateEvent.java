package top.panson.irpc.framework.core.common.event;

/**
 * @Author linhao
 * @Date created in 10:33 下午 2021/12/18
 */
public class IRpcUpdateEvent implements IRpcEvent {

    private Object data;

    public IRpcUpdateEvent(Object data) {
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
