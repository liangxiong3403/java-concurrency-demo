package top.xiangqian.concurrency.beautiful.chapter1.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class WaitNotifyInterruptTest {

    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println("---begin wait---");
                    object.wait();
                    System.out.println("---end wait---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("---begin interrupt theadA---");
        threadA.interrupt();
        System.out.println("---end interrupt theadA---");
    }
}
