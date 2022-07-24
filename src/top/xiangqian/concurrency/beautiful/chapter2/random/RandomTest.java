package top.xiangqian.concurrency.beautiful.chapter2.random;

import java.util.Random;

/**
 * @author xiangqian
 * @date 2022/7/24
 * @description:
 **/
public class RandomTest {

    private static final Integer SIZE = 10;

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
