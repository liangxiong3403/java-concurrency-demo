package top.xiangqian.concurrency.beautiful.chapter1.notify;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class NotifyAndNotifyAllTest {

    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadA get resourceA lock");
                try {
                    System.out.println("threadA begin wait");
                    resourceA.wait();
                    System.out.println("threadA end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadB get resourceA lock");
                try {
                    System.out.println("threadB begin wait");
                    resourceA.wait();
                    System.out.println("threadB end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadC begin notify");
                resourceA.notifyAll();
            }
        });

        threadA.start();
        threadB.start();
        TimeUnit.SECONDS.sleep(1);
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main thread over");
    }
}
