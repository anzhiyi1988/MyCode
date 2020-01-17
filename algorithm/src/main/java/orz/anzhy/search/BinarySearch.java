package orz.anzhy.search;

/**
 * 二分查找
 */
public class BinarySearch {
    public static int search(int[] arr, int value) {

        int right = 0;
        int left = arr.length - 1;

        while (right <= left) {

            int mid = right + (left - right) / 2;

            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                right = mid + 1;
            } else {
                left = mid - 1;
            }
        }
        return -1;

    }
}
