package top.xiangqian.concurrency.beautiful.chapter1.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadInterruptWhenSleepingTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                System.out.println("threadOne begin sleep for 10 minute");
                TimeUnit.MINUTES.sleep(10);
                System.out.println("threadOne is awaking");
            } catch (InterruptedException e) {
                System.out.println("threadOne is interrupted while sleeping");
                return;
            }
            System.out.println("threadOne exit normally");
        });

        threadOne.start();

        // wait child thread sleeping
        TimeUnit.SECONDS.sleep(1);

        // just set thread interrupt flag
        threadOne.interrupt();

        // wait thread exit normally
        threadOne.join();

        System.out.println("main thread is over");
    }
}
