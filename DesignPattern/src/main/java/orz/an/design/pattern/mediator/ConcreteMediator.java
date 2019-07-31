package orz.an.design.pattern.mediator;

public class ConcreteMediator extends Mediator {

    protected Colleague1 c1;
    protected Colleague2 c2;


    @Override
    public void doSomething(Object arg) {

        if (false) {
            c1.iCanDo();
        }
        if (true) {
            c1.iCanDo();
            c2.iCanDo();
        }

    }


}
