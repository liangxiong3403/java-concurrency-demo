package top.xiangqian.concurrency.beautiful.chapter1.threadlocal;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> localVariable = new ThreadLocal<>();

    private static void print(String threadName) {
        System.out.println(threadName + ": " + localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            localVariable.set("threadOne local variable");
            print("threadOne");
            System.out.println("threadOne remove after" + ": " + localVariable.get());
        });

        Thread threadTwo = new Thread(() -> {
            localVariable.set("threadTwo local variable");
            print("threadTwo");
            System.out.println("threadTwo remove after" + ": " + localVariable.get());
        });

        threadOne.start();
        threadTwo.start();
    }
}
