package top.xiangqian.concurrency.beautiful.chapter2.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiangqian
 * @date 2022/7/23
 * @description:
 **/
public class UnsafeTest {

    private static Unsafe unsafe;

    private volatile int state = 0;

    private static long sateOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(Boolean.TRUE);
            unsafe = (Unsafe) field.get(null);
            sateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
            System.out.println("sateOffset: " + sateOffset);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        UnsafeTest unsafeTest = new UnsafeTest();
        boolean success = unsafe.compareAndSwapInt(unsafeTest, sateOffset, 0, 1);
        System.out.println(success);
    }
}
