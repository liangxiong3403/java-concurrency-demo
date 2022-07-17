package top.xiangqian.concurrency.beautiful.chapter1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class ThreadSleepTest {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            LOCK.lock();
            try {
                System.out.println("child threadA is in sleep");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("child threadA is in awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            LOCK.lock();
            try {
                System.out.println("child threadB is in sleep");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("child threadB is in awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }
}
