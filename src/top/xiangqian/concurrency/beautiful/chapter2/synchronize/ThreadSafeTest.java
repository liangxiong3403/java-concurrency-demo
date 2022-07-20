package top.xiangqian.concurrency.beautiful.chapter2.synchronize;

/**
 * @author xiangqian
 * @date 2022/7/20
 * @description: 所谓原子性操作就是指, 一系列操作步骤, 要么全部执行, 要么全部不执行, 不会出现只执行一部分地情况
 **/
public class ThreadSafeTest {

    private Integer count;

    public synchronized Integer getCount() {
        return count;
    }

    public synchronized void increase() {
        ++this.count;
    }
}
