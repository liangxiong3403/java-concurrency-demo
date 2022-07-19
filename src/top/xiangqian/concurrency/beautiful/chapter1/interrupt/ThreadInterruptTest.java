package top.xiangqian.concurrency.beautiful.chapter1.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + "hello");
            }
        });

        thread.start();

        // wait thread print first
        TimeUnit.SECONDS.sleep(1);


        System.out.println("main thread interrupt thread");
        // just set interrupt flag
        thread.interrupt();

        // wait thread exit successful
        thread.join();
        System.out.println("main is over");
    }
}
