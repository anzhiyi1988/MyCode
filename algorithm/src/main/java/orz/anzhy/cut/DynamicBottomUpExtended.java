package orz.anzhy.cut;

import java.util.Arrays;

public class DynamicBottomUpExtended {


    public int cut(int[] price, int n) {
        int rsP[] = new int[n + 1];
        int rs[] = new int[n + 1];

        rsP[0] = 0;

        for (int i = 1; i <= n; i++) {
            int maxP = 0;
            for (int j = 1; j <= i; j++) {
                if (maxP < price[j - 1] + rsP[i - j]) {
                    maxP = price[j - 1] + rsP[i - j];
                    rs[i] = j;
                }

            }
            rsP[i] = maxP;
        }


        System.out.println(Arrays.toString(rsP));
        System.out.println(Arrays.toString(rs));

        return rsP[n];
    }
}
