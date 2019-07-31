package orz.an.design.pattern.proxy;

public class Proxy implements IPlayer {

    private IPlayer p = null;

    public Proxy(IPlayer p) {
        this.p = p;
    }


    public void doSomething() {
        before();
        p.doSomething();
        after();
        
    }

    private void after() {
    }

    private void before() {
    }
}
