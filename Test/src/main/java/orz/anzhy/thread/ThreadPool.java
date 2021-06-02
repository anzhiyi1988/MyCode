/**
 * @projectName Test
 * @package orz.anzhy.thread
 * @className orz.anzhy.thread.ThreadPool
 * @copyright anzhy.
 */
package orz.anzhy.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/2/1 18:02
 */
public class ThreadPool {

    ThreadPoolExecutor threadPoolExecutor;

    public void init() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        threadPoolExecutor = new ThreadPoolExecutor(5, 6, 1, TimeUnit.MINUTES, queue);
    }

    public void putTask(Runnable r) {
        threadPoolExecutor.execute(r);
    }





}
