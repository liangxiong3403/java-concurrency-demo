package top.xiangqian.concurrency.beautiful.chapter2.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiangqian
 * @date 2022/7/24
 * @description:
 **/
public class AtomicLongTest {

    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(10);
        System.out.println("addAndGet: " + counter.addAndGet(1));
        System.out.println("getAndAdd: " + counter.getAndAdd(1));
        System.out.println("incrementAndGet: " + counter.incrementAndGet());
        System.out.println("decrementAndGet: " + counter.decrementAndGet());
        System.out.println("getAndIncrement: " + counter.getAndIncrement());
        System.out.println("getAndDecrement: " + counter.getAndDecrement());
        System.out.println("counter: " + counter.get());
    }
}
