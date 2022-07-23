package top.xiangqian.concurrency.beautiful.chapter2.contend;

/**
 * @author xiangqian
 * @date 2022/7/23
 * @description:
 **/
public class ThreadContendedTest {

    private static final int LINE_NUM = 1024;

    private static final int COLUMN_NUM = 1024;

    public static void main(String[] args) {
        long[][] school = new long[LINE_NUM][COLUMN_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                school[j][i] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("spend time: " + (endTime - startTime));
    }
}
