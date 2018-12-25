package orz.anzhy.test;

import org.junit.Test;
import orz.anzhy.test.boolen.HasException;

public class MyTest {


    @Test
    public void testBoolen() {
        System.out.println("------------------------");
        System.out.println(HasException.isOpen(1));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------");
        System.out.println(HasException.isOpen(0));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------");
        System.out.println(HasException.isOpen(2));
    }

}
