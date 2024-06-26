package top.panson.irpc.framework.core.tolerant;

import java.util.concurrent.TimeUnit;

/**
 * @Author linhao
 * @Date created in 4:04 下午 2022/3/1
 */
public abstract class CounterLimit {

    protected int limitCount;

    protected long limitTime;

    protected TimeUnit timeUnit;

    protected volatile boolean isLimited;

    protected abstract boolean tryCount();
}
