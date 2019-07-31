package orz.an.design.pattern.chain;

public class Handler2 extends Handler {
    @Override
    protected void makeDecision(Request request) {
        request.request();

        System.out.println("speak!");
    }
}
