package orz.anzhy.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class Heap {

    // 调整，小数下沉
    private void heapify(int a[], int top, int heapSize) {

        int l = 2 * top + 1;
        int r = 2 * top + 2;

        int largest;

        if (l < heapSize && a[l] > a[top]) {
            largest = l;
        } else {
            largest = top;
        }

        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }

        if (largest != top) {
            int temp = a[top];
            a[top] = a[largest];
            a[largest] = temp;
            heapify(a, largest, heapSize);
        }
    }

    private void buildHeap(int a[]) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            heapify(a, i, a.length);
        }
    }


    public void sort(int a[]) {
        buildHeap(a);

        System.out.println(Arrays.toString(a));

        for (int i = a.length - 1; i > 0; i--) {

            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapify(a, 0, i);

        }
    }

}
