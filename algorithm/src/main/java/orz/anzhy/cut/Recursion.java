package orz.anzhy.cut;

public class Recursion {


    public int cut(int[] price, int n) {

        if (n == 0)
            return 0;

        int maxP = 0;
        for (int i = 1; i <= n; i++) {
            maxP = Math.max(maxP, price[i-1] + cut(price, n - i));
        }
        return maxP;
    }
}
