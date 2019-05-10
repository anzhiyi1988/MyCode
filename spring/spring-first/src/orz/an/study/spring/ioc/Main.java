package orz.an.study.spring.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {


    public static void testBeanFactory() {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("orz/an/study/spring/ioc/Beans.xml"));

        HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");

        obj.sayHello();
    }

    public static void testApplicationContext() {
        {
            ApplicationContext context = new ClassPathXmlApplicationContext("orz/an/study/spring/ioc/Beans.xml");

            HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

            obj.sayHello();
        }

    }


    public static void main(String[] args) {
        testApplicationContext();
        // testBeanFactory();


    }
}
