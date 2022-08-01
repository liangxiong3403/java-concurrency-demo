package top.xiangqian.concurrency.beautiful.chapter6.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xiangqian
 * @date 2022/7/31
 * @description:
 **/
public class ReentrantReadWriteLockListTest {

    private static final List<String> NUMBERS = new ArrayList<>(2);

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    private static final Lock READ_LOCK = LOCK.readLock();

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
