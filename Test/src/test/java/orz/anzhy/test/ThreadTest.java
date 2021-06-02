/**
 * @projectName Test
 * @package orz.anzhy.test
 * @className orz.anzhy.test.ThreadTest
 * @copyright anzhy.
 */
package orz.anzhy.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import orz.anzhy.thread.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ThreadTest
 *
 * @author anzhy
 * @version 1.0
 * @description 测试多线程
 * @date 2021/2/1 18:32
 */
@Slf4j
public class ThreadTest {


    @Test
    public void testThread() throws InterruptedException {


        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 6, 1, TimeUnit.MINUTES, queue);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        int i = 1;
        while (i < 101) {
            log.info("[anzhy]-[taskID:{}]", i);
            threadPoolExecutor.execute(new Worker(i, countDownLatch));

            Thread.sleep(1000);
            i++;
        }

        countDownLatch.await();

    }

    @Test
    public void testThreadSubmit() throws InterruptedException {

        int taskNum = 20;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 6, 1, TimeUnit.MINUTES, queue);
        CountDownLatch countDownLatch = new CountDownLatch(taskNum);
        int i = 0;
        List<Future> list = new ArrayList<>();
        while (i < taskNum) {
            log.info("[anzhy]-[taskID:{}]", i);
            Future f = threadPoolExecutor.submit(new Worker(i, countDownLatch));
            list.add(f);
            Thread.sleep(1000);
            i++;
        }

        countDownLatch.await();
        list.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

    }


}
