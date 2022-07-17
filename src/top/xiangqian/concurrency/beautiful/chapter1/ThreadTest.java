package top.xiangqian.concurrency.beautiful.chapter1;

/**
 * @author xiangqian
 * @date 2022/7/16
 **/
public class ThreadTest {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am a child thread");
            System.out.println(this.getId());
            System.out.println(this.getName());
        }
    }

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
    }
}
