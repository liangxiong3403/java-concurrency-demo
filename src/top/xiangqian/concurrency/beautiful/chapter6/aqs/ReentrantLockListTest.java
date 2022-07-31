package top.xiangqian.concurrency.beautiful.chapter6.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiangqian
 * @date 2022/7/31
 * @description:
 **/
public class ReentrantLockListTest {

    private static final List<String> NUMBERS = new ArrayList<>(2);

    private static final Lock LOCK = new ReentrantLock();

    private static void add(String num) {
        LOCK.lock();
        try {
            NUMBERS.add(num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static void remove(int index) {
        LOCK.lock();
        try {
            NUMBERS.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static String get(int index) {
        LOCK.lock();
        try {
            return NUMBERS.get(index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
        return null;
    }
}
