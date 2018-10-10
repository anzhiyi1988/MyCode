package orz.anzhy.sffx.monitor.dao;

import org.junit.Test;

import com.thunisoft.sffx.monitor.bean.Server;

public class ServerDaoTest {

    @Test
    public void addServTest() {
        Server server = new Server();
        server.setCip("192.168.56.101");
        server.setNid("1");
        server.setUser("root");
        server.setPasswd("@163.com");
        server.setCmonitorcmd("ping 192.168.56.101 -c 2");


    }

}
