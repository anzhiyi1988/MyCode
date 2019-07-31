package orz.an.design.pattern;

import org.junit.Test;
import orz.an.design.pattern.chain.Handler;
import orz.an.design.pattern.chain.Handler1;
import orz.an.design.pattern.chain.Handler2;
import orz.an.design.pattern.chain.Request;

public class ChainTest {

    @Test
    public void chainTest(){

        Request request = new Request();

        Handler handler1 = new Handler1();
        Handler handler2 = new Handler2();

        handler1.setNextHandler(handler2);

        handler1.handlerMessage(request);

    }

}
