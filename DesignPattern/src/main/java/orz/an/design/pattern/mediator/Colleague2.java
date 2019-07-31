package orz.an.design.pattern.mediator;

public class Colleague2 extends  Colleague {
    public Colleague2(Mediator mediator) {
        super(mediator);
    }

    public void iCanDo() {

    }

    public void work() {
        mediator.doSomething(new Object());
    }



}
