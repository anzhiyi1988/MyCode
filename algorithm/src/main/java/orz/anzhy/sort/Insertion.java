package orz.anzhy.sort;

public class Insertion {


    public void sort(int a[]) {

        for (int i = 1; i < a.length; i++) {

            int get = a[i];

            int j = i - 1;
            while (j >= 0 && a[j] > get) {

                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = get;
        }
    }
}
