package top.xiangqian.concurrency.beautiful.chapter6.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xiangqian
 * @date 2022/8/1
 * @description: ReentrantReadWriteLock实例代码
 **/
public class ReentrantReadWriteLockListTest {

    private static final List<String> NUMBERS = new ArrayList<>(10);

    /**
     * static final int SHARED_SHIFT   = 16;
     * static final int SHARED_UNIT    = (1 << SHARED_SHIFT) = 65536;
     * static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1 = 65535;
     * AQS状态值state的高16位: 表示获取读锁的线程个数,最大值: SHARED_UNIT = 65536
     * AQS状态值state的低16位: 表示获取到写锁的线程可重入次数,最大值: MAX_COUNT = (1 << SHARED_SHIFT) - 1 = 65535;
     */
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    /**
     * 读锁是共享锁(当前如果有其他线程获取写锁,则当前线程无法获取读锁)
     */
    private static final Lock READ_LOCK = LOCK.readLock();

    /**
     * 写锁是排他锁,可重入(当前如果有其他线程获取了读锁或写锁,则当前线程无法获取写锁)
     */
    private static final Lock WRITE_LOCK = LOCK.writeLock();

    private static void add(String num) {
        WRITE_LOCK.lock();
        try {
            NUMBERS.add(num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    private static void remove(int index) {
        WRITE_LOCK.lock();
        try {
            NUMBERS.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    private static String get(int index) {
        READ_LOCK.lock();
        try {
            return NUMBERS.get(index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            READ_LOCK.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        NUMBERS.add("1");
        NUMBERS.add("21");
        NUMBERS.add("31");
        System.out.println(NUMBERS.get(2));
    }
}
