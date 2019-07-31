package orz.an.design.pattern.chain;

public class Handler1 extends Handler {
    @Override
    protected void makeDecision(Request request) {
        request.request();

        System.out.println("滚！");

    }
}
