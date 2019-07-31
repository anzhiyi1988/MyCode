package orz.an.design.pattern;

import org.junit.Test;
import orz.an.design.pattern.observer.ConcreteObserver;
import orz.an.design.pattern.observer.ConcreteSubject;

import java.util.Observer;

public class ObserverTest {

    @Test
    public void testObserver() {

        ConcreteSubject observable = new ConcreteSubject();

        Observer observer = new ConcreteObserver();
        observable.addObserver(observer);

        observable.doSomething();


    }

}
