package orz.anzhy.sort;

/**
 *
 * 冒泡排序
 *
 */
public class Bubble {

    public void sort(int a[]) {

        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
