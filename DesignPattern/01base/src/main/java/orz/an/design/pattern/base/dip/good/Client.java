package orz.an.design.pattern.base.dip.good;

import orz.an.design.pattern.base.dip.bad.Benz;
import orz.an.design.pattern.base.dip.bad.Driver;

public class Client {

    public static void main(String[] arg) {

        IDriver d = new Driver();

        ICar benz = new Benz();

        d.drive(benz);

    }


}
