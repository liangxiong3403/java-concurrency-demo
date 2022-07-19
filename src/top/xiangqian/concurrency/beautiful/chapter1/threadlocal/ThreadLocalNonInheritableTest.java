package top.xiangqian.concurrency.beautiful.chapter1.threadlocal;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadLocalNonInheritableTest {

    private static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        localVariable.set("main thread say hello world");

        Thread childThread = new Thread(() -> {
            System.out.println("childThread get localVariable: " + localVariable.get());
        });

        childThread.start();
        childThread.join();
        System.out.println("main thread get localVariable: " + localVariable.get());
    }
}
