package top.xiangqian.concurrency.beautiful.chapter1.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @Author liangxiong
 * @Project
 * @Description 1.互斥性 一个线程对持有资源的使用具有排他性,资源在同一时刻只能被一个线程持有,其他线程申请这个使用中的资源必须阻塞等待
 * @Description 2.持有并等待 一个线程至少持有一个资源,同时申请另外地资源;另外地资源此时正在被其他线程所持有,所以当前线程必须等待
 * @Description 3.不可剥夺 一个线程持有地资源必须使用完后才能被其释放,在其持有期间不会被其他线程所抢占
 * @Description 4.环形等待 t0持有并等待t1 t1持有并等待t2 t2......tn tn持有并等待t0
 * @Date 2022-07-19
 */
public class ThreadDeadLockTest {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (resourceB) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadA get resourceB lock");
                System.out.println("threadA try to get resourceA lock");
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB get resourceA lock");
                System.out.println("threadB try to get resourceB lock");
                synchronized (resourceB) {
                    System.out.println("threadB get resourceB lock");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
