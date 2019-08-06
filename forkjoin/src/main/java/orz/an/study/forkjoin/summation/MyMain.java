package orz.an.study.forkjoin.summation;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class MyMain {

    public static void main(String[] arg) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> result = pool.submit(new SumTask(arr, 0, arr.length));
        System.out.println(result.invoke());
        pool.shutdown();


    }

}
