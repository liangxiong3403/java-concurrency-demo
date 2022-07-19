package top.xiangqian.concurrency.beautiful.chapter1.daemon;

/**
 * @Author liangxiong
 * @Project
 * @Description
 * @Date 2022-07-19
 */
public class ThreadDaemonTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
            }
        });

        // set thread daemon
        thread.setDaemon(Boolean.TRUE);
        thread.start();

        System.out.println("main thread is over");
    }
}
