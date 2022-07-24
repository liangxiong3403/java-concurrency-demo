package top.xiangqian.concurrency.beautiful.chapter2.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xiangqian
 * @date 2022/7/24
 * @description:
 **/
public class ThreadLocalRandomTest {

    private static final Integer SIZE = 10;

    public static void main(String[] args) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < SIZE; i++) {
            System.out.println(threadLocalRandom.nextInt(5));
        }
    }
}
