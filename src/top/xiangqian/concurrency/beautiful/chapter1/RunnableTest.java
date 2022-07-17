package top.xiangqian.concurrency.beautiful.chapter1;

/**
 * @author xiangqian
 * @date 2022/7/16
 **/
public class RunnableTest {

    public static class RunnableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("I am a child thread");
            System.out.println(Thread.currentThread().getId());
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new RunnableTask();
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread2.start();
        thread3.start();
    }
}
