package top.xiangqian.concurrency.beautiful.chapter1.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @Author liangxiong
 * @Project
 * @Description 多个线程对资源加锁顺序保持一致
 * @Date 2022-07-19
 */
public class ThreadDeadLockSolutionTest {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (resourceB) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadA get resourceB lock");
                System.out.println("threadA try to get resourceA lock");
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceB) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB get resourceB lock");
                System.out.println("threadB try to get resourceA lock");
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
