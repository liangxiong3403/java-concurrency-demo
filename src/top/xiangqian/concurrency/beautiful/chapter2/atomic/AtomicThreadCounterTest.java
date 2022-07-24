package top.xiangqian.concurrency.beautiful.chapter2.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiangqian
 * @date 2022/7/24
 * @description:
 **/
public class AtomicThreadCounterTest {

    private static final AtomicLong COUNTER = new AtomicLong(0);

    private static final Integer[] students = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    private static final Integer[] teachers = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            int size = students.length;
            for (int i = 0; i < size; i++) {
                if (students[i].intValue() == 0) {
                    COUNTER.incrementAndGet();
                }
            }
        });
        Thread threadTwo = new Thread(() -> {
            int size = teachers.length;
            for (int i = 0; i < size; i++) {
                if (teachers[i].intValue() == 0) {
                    COUNTER.incrementAndGet();
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("COUNTER: " + COUNTER.get());
    }
}
