package top.panson.irpc.framework.core.tolerant.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author linhao
 * @Date created in 4:16 下午 2022/3/1
 */
public class CounterLimitTest {

    private static FixedWindowCounterLimit fIxedWindowCounterLimit = new FixedWindowCounterLimit(10,60, TimeUnit.SECONDS);

    private static SlidingWindowCounterLimit slidingWindowCounterLimit = new SlidingWindowCounterLimit(20,10,10);

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!slidingWindowCounterLimit.tryCount()){
                        return;
                    }
                    System.out.println("执行核心任务！");
                }
            }).start();
        }
        countDownLatch.countDown();
        System.out.println("启动并发测试");
        Thread.yield();
    }
}
