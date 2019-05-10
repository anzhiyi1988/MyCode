package orz.an.study.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("orz/an/study/spring/aop/Beans.xml");

        Son son = (Son) context.getBean("son");

        son.study();

        son.play();

        son.wrong();




    }
}
