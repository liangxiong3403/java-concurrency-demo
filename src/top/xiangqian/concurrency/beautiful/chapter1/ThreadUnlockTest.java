package top.xiangqian.concurrency.beautiful.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class ThreadUnlockTest {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadA get resourceA lock");
                synchronized (resourceB) {
                    System.out.println("threadA get resourceB lock ");
                    System.out.println("threadA release resourceA lock");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("threadB get resourceA lock");
                System.out.println("threadB try to get resourceB lock");
                synchronized (resourceB) {
                    System.out.println("threadB get resourceB lock");
                    System.out.println("threadB release resourceA lock");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("main thread over");
    }
}
