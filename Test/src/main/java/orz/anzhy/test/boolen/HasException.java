package orz.anzhy.test.boolen;

public class HasException {


    public static String isOpen(int b) {
        int a = 0;

        try {
            a = 1 / b;
            System.out.println("a");
            return "1true";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("b");
            return "2false";
        } finally {
            if (a == 1) {
                System.out.println("c");
                return "3true";
            } else {
                System.out.println("d");
                return "4false";
            }
        }

    }

}
