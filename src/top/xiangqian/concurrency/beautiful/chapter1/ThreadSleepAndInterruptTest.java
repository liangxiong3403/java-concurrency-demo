package top.xiangqian.concurrency.beautiful.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class ThreadSleepAndInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                System.out.println("child threadA is in sleep");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("child threadA is in awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        TimeUnit.SECONDS.sleep(2);
        threadA.interrupt();
    }
}
