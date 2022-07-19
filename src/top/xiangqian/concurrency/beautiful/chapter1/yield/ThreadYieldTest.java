package top.xiangqian.concurrency.beautiful.chapter1.yield;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadYieldTest implements Runnable {

    private static final Integer SIZE = 5;

    public ThreadYieldTest() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < SIZE; i++) {
            if (i % SIZE == 0) {
                System.out.println(Thread.currentThread() + "yield cpu");
                // give up cpu scheduling
                Thread.yield();
            }
            System.out.println(Thread.currentThread() + "is over");
        }
    }

    public static void main(String[] args) {
        new ThreadYieldTest();
        new ThreadYieldTest();
        new ThreadYieldTest();
    }

}
