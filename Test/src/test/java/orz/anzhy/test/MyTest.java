package orz.anzhy.test;

import org.junit.Test;
import orz.anzhy.code.Code;
import orz.anzhy.option.Process;
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


    @Test
    public void testOption(){


        Process p = new Process();

        try {
            p.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 学习枚举类型，之前一直没有好好的去做测试
     */
    @Test
    public void testEnumType(){
        Code c = Code.C1;
        System.out.println(c.name());
        System.out.println(c.getValue());
        System.out.println(c.getName());

        System.out.println(Code.values()[2]);
    }

}
