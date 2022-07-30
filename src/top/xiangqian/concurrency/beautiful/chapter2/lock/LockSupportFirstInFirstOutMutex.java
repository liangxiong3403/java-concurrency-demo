package top.xiangqian.concurrency.beautiful.chapter2.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiangqian
 * @date 2022/7/30
 * @description: LockSupport类与每个使用它的线程都会关联一个许可证, 默认情况下调用LockSupport的类中方法的线程不持有许可证
 **/
public class LockSupportFirstInFirstOutMutex {

    private final AtomicBoolean locked = new AtomicBoolean(Boolean.FALSE);

    private final Queue<Thread> waiter = new ConcurrentLinkedQueue<>();

    private volatile Thread current;

    /**
     * lock
     */
    public void lock() {
        boolean isInterrupted = Boolean.FALSE;
        waiter.add(current);
        while (waiter.peek() != current || !locked.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
            // 如果队列第一个元素不是当前线程或者当前线程加锁失败
            LockSupport.park(this);
            if (Thread.interrupted()) {
                // 如果当前线程被中断则清除中断标记,使用变量保存当前线程的中断状态
                isInterrupted = Boolean.TRUE;
            }
        }
        // 消费条件满足
        Thread head = waiter.remove();
        head.start();
        // 检查中断标记,这个中断状态可能被其他线程需要
        if (isInterrupted) {
            head.interrupt();
        }
    }

    /**
     * unlock
     */
    public void unlock() {
        locked.set(Boolean.FALSE);
        LockSupport.unpark(waiter.peek());
    }

    public void setCurrent(Thread current) {
        this.current = current;
    }
}
