package top.xiangqian.concurrency.beautiful.chapter6.aqs;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xiangqian
 * @date 2022/7/31
 * @description: 不可重入独占锁
 **/
public class NonReentrantLock implements Lock, Serializable {

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        @Override
        public boolean tryRelease(int acquires) {
            assert acquires == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return Boolean.TRUE;
        }

        @Override
        public boolean isHeldExclusively() {
            return getState() == 1;
        }

        ConditionObject newConditionObject() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    /**
     * 获取条件变量
     *
     * @return
     */
    @Override
    public Condition newCondition() {
        return sync.newConditionObject();
    }
}
