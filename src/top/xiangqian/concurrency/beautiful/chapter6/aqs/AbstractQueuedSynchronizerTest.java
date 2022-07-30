package top.xiangqian.concurrency.beautiful.chapter6.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiangqian
 * @date 2022/7/30
 * @description: AbstractQueuedSynchronizer抽象队列同步器简称为AQS
 * 是实现同步器的基础组件
 * 并发包中锁的底层就是基于AbstractQueuedSynchronizer实现地
 * 大多数开发者可能永远也不会直接使用ASQ
 * 但是掌握其原理对于架构设计还是很有帮助地
 **/
public class AbstractQueuedSynchronizerTest {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition conditionObject = lock.newCondition();
        Thread threadOne = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("begin await");
                conditionObject.await();
                System.out.println("end await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        threadOne.start();

        TimeUnit.MILLISECONDS.sleep(200);

        Thread threadTwo = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("begin signal");
                conditionObject.signal();
                System.out.println("end signal");
            } catch (IllegalMonitorStateException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        threadTwo.start();
    }
}
