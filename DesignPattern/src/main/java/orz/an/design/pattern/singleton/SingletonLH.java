package orz.an.design.pattern.singleton;

/**
 * 懒汉模式
 */
public class SingletonLH {


    private static SingletonLH singletonLH = null;

    private SingletonLH() {

    }


    public static synchronized SingletonLH getInstance() {
        if (singletonLH == null) {
            singletonLH = new SingletonLH();
        }
        return singletonLH;
    }


}
