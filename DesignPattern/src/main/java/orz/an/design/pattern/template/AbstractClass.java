package orz.an.design.pattern.template;


/**
 * 模板方法
 */
public abstract class AbstractClass {

    protected abstract void step1();

    protected abstract void step2();

    protected abstract void step3();

    public void templateMethod() {

        step1();

        step2();

        step3();

    }

}
