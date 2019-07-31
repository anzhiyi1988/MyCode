package orz.an.design.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class ConcreteObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        System.out.println("观察者监测到：" + arg);

    }
}
