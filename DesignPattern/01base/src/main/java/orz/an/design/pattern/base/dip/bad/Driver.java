package orz.an.design.pattern.base.dip.bad;

import orz.an.design.pattern.base.dip.good.ICar;
import orz.an.design.pattern.base.dip.good.IDriver;

public class Driver implements IDriver {
    public void drive(Benz benz) {
    }

    public void drive(ICar car) {

    }
}
