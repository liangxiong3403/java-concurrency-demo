package top.xiangqian.concurrency.beautiful.chapter1.join;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangqian
 * @date 2022/7/17
 * @description:
 **/
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("threadOne sleep over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadTwo = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("threadTwo sleep over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over");

        threadOne.join();
        threadTwo.join();
        System.out.println("all child thread over");
    }
}
