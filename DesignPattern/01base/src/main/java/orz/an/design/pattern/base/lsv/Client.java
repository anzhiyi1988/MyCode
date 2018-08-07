package orz.an.design.pattern.base.lsv;


/**
 * @author anzhy
 * @version 1.0
 * @created 7-8-2018 11:52:55
 */
public class Client {


    public static void main(String[] arg) {
        Soldier soldier = new Soldier();
        soldier.killEnemy(new Handgun());
    }


}