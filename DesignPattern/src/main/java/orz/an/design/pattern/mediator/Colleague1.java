package orz.an.design.pattern.mediator;

public class Colleague1 extends Colleague {
    public Colleague1(Mediator mediator) {
        super(mediator);
    }

    public void iCanDo() {

    }

    public void needMediator() {
        mediator.doSomething(new Object());
        }

        }
