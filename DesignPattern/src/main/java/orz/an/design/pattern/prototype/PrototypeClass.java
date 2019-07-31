package orz.an.design.pattern.prototype;


/**
 * 原型模式
 */
public class PrototypeClass implements Cloneable {

    @Override
    public PrototypeClass clone() {

        PrototypeClass p = null;
        try {
            p = (PrototypeClass) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return p;

    }


}
