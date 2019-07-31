package orz.an.design.pattern.base.lsv;


/**
 * @author anzhy
 * @version 1.0
 * @created 07-8��-2018 11:53:04
 */
public class Soldier {


    /**
     * @param gun
     */
    public void killEnemy(AbstractGun gun) {
        gun.shoot();
    }

}