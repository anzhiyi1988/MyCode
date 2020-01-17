package orz.anzhy.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Quick {


    private int part(int a[], int l, int r) {
        int x = a[r];
        int s = l - 1; // 前部分当前的尾巴
        for (int i = l; i < r; i++) {
            if (a[i] < x) { // 小的放到前部分的尾巴
                s++;
                int temp = a[s];
                a[s] = a[i];
                a[i] = temp;
            }
        }

        int temp = a[s + 1];
        a[s + 1] = a[r];
        a[r] = temp;
        System.out.println(Arrays.toString(a));
        return s + 1;
    }

    public void sort(int a[], int left, int right) {
        if (left < right) {
            int pivot = part(a, left, right);
            sort(a, left, pivot - 1);
            sort(a, pivot + 1, right);
        }
    }


}
