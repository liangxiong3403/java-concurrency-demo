package top.xiangqian.concurrency.beautiful.chapter1.wait;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class ThreadWaitTest {

    private static final Integer MAX_SIZE = 1;

    private static Queue<String> queue = new ArrayBlockingQueue<>(MAX_SIZE, true);

    public static void produceAndConsumer() {
        synchronized (queue) {
            // get monitor lock
            while (queue.size() == MAX_SIZE) {
                // avoid fake awake, release monitor lock
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add("I am a apple");
            System.out.println("notify other thread, wait consumer");
            queue.notifyAll();
        }

        synchronized (queue) {
            // get monitor lock
            while (queue.size() == 0) {
                // avoid fake awake, release monitor lock
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("consume element: " + queue.remove());
            System.out.println("notify other thread, wait producer");
            queue.notifyAll();
        }

        System.out.println(queue.toString());
    }

    public static void main(String[] args) {
        produceAndConsumer();
    }
}
