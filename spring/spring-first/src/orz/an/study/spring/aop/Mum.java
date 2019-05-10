package orz.an.study.spring.aop;

public class Mum {

    public void before() {
        System.out.println("Mum : what are you doing ? honey !");
    }


    public void after() {
        System.out.println("Mum : go home ");
    }

    public void afterReturn(Object retVal) {
        System.out.println(retVal);
        System.out.println("Mum : so a lang time !");
    }


    public void afterEx(IllegalArgumentException ex) {
        System.out.println("Mum : what is wrong !");
    }


    public void around() {
        System.out.println("Mum : glimpse ");
    }

}
