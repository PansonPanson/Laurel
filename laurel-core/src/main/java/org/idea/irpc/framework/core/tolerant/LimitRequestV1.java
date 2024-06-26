package top.panson.irpc.framework.core.tolerant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流器
 *
 * @Author linhao
 * @Date created in 8:41 上午 2022/2/28
 */
public class LimitRequestV1 {

    private int limit;

    private int times;

    private AtomicInteger request = new AtomicInteger(0);

    private TimeUnit timeUnit;

    public LimitRequestV1(int limit, int times, TimeUnit timeUnit) {
        this.limit = limit;
        this.times = times;
        this.timeUnit = timeUnit;
    }

    public void doRequest() {
        synchronized (this) {
            if (request.get() >= limit) {
                throw new RuntimeException("请求超过限制流量");
            }
            request.incrementAndGet();
        }
    }

    public void afterRequest() {
        synchronized (this) {
            request.decrementAndGet();
        }
    }

    public void testMethod() {
        this.doRequest();
        System.out.println("请求处理函数");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.afterRequest();
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        LimitRequestV1 limitRequest = new LimitRequestV1(10, 1, TimeUnit.SECONDS);
        for (int i = 0; i < 13; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    limitRequest.testMethod();
                }
            });
            thread.start();
        }
        countDownLatch.countDown();
        System.out.println("============");
        Thread.yield();
    }
}
