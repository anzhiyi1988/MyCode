package orz.an.design.pattern.base.dip.bad;



public class Client {

    public static void main(String[] arg){

        Driver d = new Driver();
        Benz benz = new Benz();
        d.drive(benz);

    }

}
