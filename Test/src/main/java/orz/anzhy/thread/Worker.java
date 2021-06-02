/**
 * @projectName Test
 * @package orz.anzhy.thread
 * @className orz.anzhy.thread.TaskThread
 * @copyright anzhy.
 */
package orz.anzhy.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * TaskThread
 *
 * @author anzhy
 * @version 1.0
 * @description
 * @date 2021/2/1 18:34
 */
@Slf4j
public class Worker implements Runnable {

    private int taskId;
    private CountDownLatch countDownLatch;

    public Worker(int i, CountDownLatch countDownLatch) {
        this.taskId = i;
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {

        log.info("[anzhy]-[ThreadName:{}]-[Class:{}]-[TaskId:{} start]", Thread.class.getName(), this.hashCode(), this.taskId);
        Thread.sleep(5000);
        log.info("[anzhy]-[ThreadName:{}]-[Class:{}]-[TaskId:{} finish]", Thread.class.getName(), this.hashCode(), this.taskId);

        countDownLatch.countDown();

        String e = String.format("[anzhy]-[ThreadName:%s]-[Class:%s]-[TaskId:%s exception]", Thread.class.getName(), this.hashCode(), this.taskId);
        //throw new Exception(e);
    }


}
