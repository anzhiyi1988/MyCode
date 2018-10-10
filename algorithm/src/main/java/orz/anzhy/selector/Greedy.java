package orz.anzhy.selector;


public class Greedy {


    public String greedy(int[] s, int[] f) {

        int n = s.length;

        String rs = "0";
        int k = 0;
        for (int i = 1; i < n; i++) {

            if (s[i] >= f[k]) {
                rs = rs + " " + i;
                k = i;
            }
        }
        return rs;

    }
}
