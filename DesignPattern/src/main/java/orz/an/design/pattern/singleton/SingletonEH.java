package orz.an.design.pattern.singleton;

/**
 * 饿汉模式
 */
public class SingletonEH {
    private static final SingletonEH SINGLETON_EH = new SingletonEH();

    private SingletonEH() {

    }

    public static SingletonEH getInstance() {
        return SINGLETON_EH;
    }
}
