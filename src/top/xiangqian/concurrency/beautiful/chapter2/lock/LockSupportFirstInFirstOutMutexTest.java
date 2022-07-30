package top.xiangqian.concurrency.beautiful.chapter2.lock;

/**
 * @author xiangqian
 * @date 2022/7/30
 * @description: LockSupport类与每个使用它的线程都会关联一个许可证, 默认情况下调用LockSupport的类中方法的线程不持有许可证
 **/
public class LockSupportFirstInFirstOutMutexTest {

    /**
     * 唤醒因为调用park方法而被阻塞地线程:
     * 1.通过调用阻塞线程的interrupt方法;  2.其他线程调用unpark方法,把阻塞线程作为参数传递过去;  3.虚假唤醒
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) {
        LockSupportFirstInFirstOutMutex queue = new LockSupportFirstInFirstOutMutex();
        Thread threadOne = new Thread(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName());
        }, "threadOne");
        Thread threadTwo = new Thread(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName());
        }, "threadTwo");

        queue.setCurrent(threadOne);
        queue.lock();
        queue.unlock();
        queue.setCurrent(threadTwo);
        queue.lock();
        queue.unlock();
    }
}
