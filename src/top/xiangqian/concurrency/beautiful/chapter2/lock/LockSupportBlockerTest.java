package top.xiangqian.concurrency.beautiful.chapter2.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiangqian
 * @date 2022/7/30
 * @description: LockSupport类与每个使用它的线程都会关联一个许可证, 默认情况下调用LockSupport的类中方法的线程不持有许可证
 **/
public class LockSupportBlockerTest {

    private void parkDebug() {
        System.out.println("begin park");
        LockSupport.park(this);
        System.out.println("end park");
    }

    public static void main(String[] args) {
        LockSupportBlockerTest debug = new LockSupportBlockerTest();
        debug.parkDebug();
    }
}
