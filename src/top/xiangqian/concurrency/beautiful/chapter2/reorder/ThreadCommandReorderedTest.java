package top.xiangqian.concurrency.beautiful.chapter2.reorder;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangqian
 * @date 2022/7/23
 * @description:
 **/
public class ThreadCommandReorderedTest {

    private static int num = 0;

    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        Thread readThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() && ready) {
                System.out.println("total: " + (num + num));
            }
            System.out.println("readThread is over");
        });

        Thread writeThread = new Thread(() -> {
            num = 2;
            ready = true;
            System.out.println("writeThread is over");
        });

        readThread.start();
        writeThread.start();

        TimeUnit.MICROSECONDS.sleep(500);
        readThread.interrupt();
    }
}
