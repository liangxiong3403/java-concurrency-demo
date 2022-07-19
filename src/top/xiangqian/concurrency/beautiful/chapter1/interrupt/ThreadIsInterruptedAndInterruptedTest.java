package top.xiangqian.concurrency.beautiful.chapter1.interrupt;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadIsInterruptedAndInterruptedTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            for (; ; ) {
            }
        });


        threadOne.start();

        // just set thread interrupt flag
        threadOne.interrupt();

        System.out.println("threadOne isInterrupted: " + threadOne.isInterrupted());
        // get current thread interrupt flag
        System.out.println("threadOne interrupted: " + threadOne.interrupted());
        System.out.println("main interrupted: " + Thread.interrupted());
        System.out.println("threadOne isInterrupted: " + threadOne.isInterrupted());

        threadOne.join();
        System.out.println("main thread is over");
    }
}
