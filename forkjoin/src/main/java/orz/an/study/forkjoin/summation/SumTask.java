package orz.an.study.forkjoin.summation;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

    private static final int MIN_NUM = 10;
    private final int[] arr;
    private final int start;
    private final int end;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    protected Integer compute() {

        if (end - start < MIN_NUM) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];

            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            SumTask left = new SumTask(arr, start, middle);
            SumTask right = new SumTask(arr, middle, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
