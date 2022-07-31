package top.xiangqian.concurrency.beautiful.chapter6.aqs;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @author xiangqian
 * @date 2022/7/31
 * @description:
 **/
public class NonReentrantLockTest {

    private static final NonReentrantLock LOCK = new NonReentrantLock();

    private static final Condition NOT_EMPTY = LOCK.newCondition();

    private static final Condition NOT_FULL = LOCK.newCondition();

    private static final Integer QUEUE_SIZE = 10;

    private static final Queue<String> QUEUE = new LinkedBlockingQueue<>(QUEUE_SIZE);


    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            System.out.println("producer begin");
            LOCK.lock();
            try {
                while (QUEUE.size() == QUEUE_SIZE) {
                    // wait consumer
                    NOT_EMPTY.await();
                }
                QUEUE.add("hello world");
                // notify consumer
                NOT_FULL.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
            System.out.println("producer end");
        });

        Thread consumer = new Thread(() -> {
            System.out.println("consumer begin");
            LOCK.lock();
            try {
                while (QUEUE.size() == 0) {
                    NOT_FULL.await();
                }
                String message = QUEUE.poll();
                System.out.println("message: " + message);
                // notify producer
                NOT_EMPTY.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
            System.out.println("consumer end");
        });

        producer.start();

        TimeUnit.MILLISECONDS.sleep(200);

        consumer.start();
    }
}
