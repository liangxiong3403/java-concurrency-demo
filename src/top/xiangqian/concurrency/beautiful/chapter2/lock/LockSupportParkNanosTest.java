package top.xiangqian.concurrency.beautiful.chapter2.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiangqian
 * @date 2022/7/30
 * @description: LockSupport类与每个使用它的线程都会关联一个许可证, 默认情况下调用LockSupport的类中方法的线程不持有许可证
 **/
public class LockSupportParkNanosTest {

    /**
     * 唤醒因为调用park方法而被阻塞地线程:
     * 1.通过调用阻塞线程的interrupt方法;  2.其他线程调用unpark方法,把阻塞线程作为参数传递过去;  3.虚假唤醒
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("begin park");
            LockSupport.parkNanos(10000);
            System.out.println("end park");
        });

        thread.start();
    }
}
