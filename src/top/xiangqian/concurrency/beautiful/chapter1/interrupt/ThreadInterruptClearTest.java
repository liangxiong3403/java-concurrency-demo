package top.xiangqian.concurrency.beautiful.chapter1.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadInterruptClearTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            // clear current thread interrupt flag
            while (!Thread.currentThread().interrupted()) {
            }
            System.out.println("threadOne isInterrupted: " + Thread.currentThread().isInterrupted());
        });

        threadOne.start();

        TimeUnit.SECONDS.sleep(1);
        threadOne.interrupt();

        // wait thread exit normally
        threadOne.join();
        System.out.println("main thread is over");
    }
}
