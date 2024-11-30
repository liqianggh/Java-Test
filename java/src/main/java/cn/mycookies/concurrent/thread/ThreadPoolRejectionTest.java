package cn.mycookies.concurrent.thread;


import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池拒绝策略测试
 *
 * @author liqiang
 * @date 2021-07-05 下午5:26
 **/
public class ThreadPoolRejectionTest {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolRejectionTest.class);
    private static final AtomicInteger counter = new AtomicInteger();
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
            3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            (r) -> new Thread(r, counter.addAndGet(1) + " 号线程 "),
            (r, executor) -> {
                throw new RuntimeException("线程池已满-" + r.toString());
            });

    private static final ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1, 1,
            3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            (r) -> new Thread(r, counter.addAndGet(1) + " 号线程 "),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("外部  " + Thread.currentThread().getName());

            CompletableFuture.supplyAsync(() -> {
                System.out.println("内部执行  " + Thread.currentThread().getName());
                return 3;
            }, threadPoolExecutor).join();
            return 1;

        }, threadPoolExecutor);
        System.out.println("主线程执行完毕");
    }

}
