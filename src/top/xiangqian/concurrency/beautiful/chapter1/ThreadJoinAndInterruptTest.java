package top.xiangqian.concurrency.beautiful.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class ThreadJoinAndInterruptTest {

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            System.out.println("threadOne begin run");
            for (; ; ) {
            }
        });

        final Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("threadTwo begin run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt();
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
        } catch (InterruptedException e) {
            System.out.println("main thread: " + e);
        }
    }
}
