/**
 * @projectName Test
 * @package orz.anzhy
 * @className orz.anzhy.Process
 * @copyright anzhy.
 */
package orz.anzhy;

import lombok.extern.slf4j.Slf4j;
import orz.anzhy.thread.Worker;

import java.util.concurrent.*;

/**
 * Process
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/2/2 10:17
 */
@Slf4j
public class Process {

    public static void main(String[] args) throws InterruptedException {


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
}
