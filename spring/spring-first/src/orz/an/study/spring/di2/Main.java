package orz.an.study.spring.di2;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("orz/an/study/spring/di2/Beans.xml");
        MyLogic logic = (MyLogic) context.getBean("myLogic");
        logic.process();


    }
}
