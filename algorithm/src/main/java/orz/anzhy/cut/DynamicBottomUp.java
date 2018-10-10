package orz.anzhy.cut;

public class DynamicBottomUp {


    public int cut(int[] price, int n) {
        int rsP[] = new int[n + 1];

        rsP[0] = 0;

        for (int i = 1; i <= n; i++) {
            int maxP = 0;
            for (int j = 1; j <= i; j++) {
                maxP = Math.max(maxP, price[j - 1] + rsP[i - j]);
            }
            rsP[i] = maxP;
        }
        return rsP[n];
    }
}
