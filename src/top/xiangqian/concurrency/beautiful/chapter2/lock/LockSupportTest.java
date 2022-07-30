package top.xiangqian.concurrency.beautiful.chapter2.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiangqian
 * @date 2022/7/30
 * @description: LockSupport类与每个使用它的线程都会关联一个许可证, 默认情况下调用LockSupport的类中方法的线程不持有许可证
 **/
public class LockSupportTest {

    /**
     * 唤醒因为调用park方法而被阻塞地线程:
     * 1.通过调用阻塞线程的interrupt方法;  2.其他线程调用unpark方法,把阻塞线程作为参数传递过去;  3.虚假唤醒
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("begin park");
            /*
             * 如果调用unpark方法地线程没有持有与LockSupport相关联地许可证,在调用unpark方法以后,就会让它持有许可证
             * LockSupport.unpark(Thread.currentThread());
             * 如果调用park方法的线程已经拿到了与LockSupport类关联地许可证, 就会立刻返回, 否则调用线程就会被禁止参与线程调度, 然后阻塞挂起
             */
            LockSupport.park();
            System.out.println("end park");
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(thread);
    }
}
