package orz.anzhy.sort;

public class Merge {


    private void merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int arr1[] = new int[n1 + 1];

        int n2 = r - q;
        int arr2[] = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            arr1[i] = arr[p + i];
        }
        arr1[n1] = Integer.MAX_VALUE;


        for (int i = 0; i < n1; i++) {
            arr2[i] = arr[q + 1 + i];
        }
        arr2[n2] = Integer.MAX_VALUE;

        int i = 0, j = 0;

        for (int k = p; k <= r; k++) {
            if (arr1[i] < arr2[j]) {
                arr[k] = arr1[i];
                i++;
            } else {
                arr[k] = arr2[j];
                j++;
            }
        }
    }

    public void sort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (int) Math.floor((p + r) / 2);
            sort(arr, p, q);
            sort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }
}
