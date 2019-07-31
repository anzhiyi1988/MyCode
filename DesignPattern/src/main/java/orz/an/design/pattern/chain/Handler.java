package orz.an.design.pattern.chain;

public abstract class Handler {

    private Handler nextHandler;

    public final void handlerMessage(Request request) {

        makeDecision(request);

        if (nextHandler != null) {

            nextHandler.handlerMessage(request);
        }
    }

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    protected abstract void makeDecision(Request request);


}
