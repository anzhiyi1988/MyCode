package orz.an.design.pattern.observer;


import java.util.Observable;

public class ConcreteSubject extends Observable {


    public void doSomething() {
        System.out.println("被观察：嘚瑟一下！");

        setChanged();
        notifyObservers("被观察：嘚瑟一下！");
    }


}
