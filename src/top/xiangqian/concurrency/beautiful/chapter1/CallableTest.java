package top.xiangqian.concurrency.beautiful.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author xiangqian
 * @date 2022/7/16
 **/
public class CallableTest {

    public static class CallableTask implements Callable<String> {
        @Override
        public String call() {
            return "I am a child thread";
        }
    }

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new CallableTask());
        new Thread(futureTask).start();
        String result;
        try {
            result = futureTask.get();
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
