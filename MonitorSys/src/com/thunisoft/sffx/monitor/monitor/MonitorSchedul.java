package com.thunisoft.sffx.monitor.monitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ch.ethz.ssh2.Connection;

import com.thunisoft.sffx.monitor.Consts;
import com.thunisoft.sffx.monitor.bean.App;
import com.thunisoft.sffx.monitor.bean.Server;
import com.thunisoft.sffx.monitor.cache.ICache;
import com.thunisoft.sffx.monitor.cache.ShareCache;
import com.thunisoft.sffx.monitor.dao.MonitorDao;
import com.thunisoft.sffx.monitor.util.Util;

@Service
public class MonitorSchedul {

    private static final Logger logger = LoggerFactory
            .getLogger(MonitorSchedul.class);

    @Autowired
    @Qualifier("monitorDao")
    MonitorDao monitorDao;

    private static final Map<String, Connection> serverConnMap = new HashMap<String, Connection>();

    private static final Map<String, Server> serverServerMap = new HashMap<String, Server>();

    private static final Map<String, List<App>> serverAppMap = new HashMap<String, List<App>>();

    private static final List<Server> serverList = new ArrayList<Server>();

    private static final List<App> appList = new ArrayList<App>();

    public boolean init() {
        boolean flag = false;
        logger.info("读取xml，初始化服务器和应用");
        flag = initServAndApp();
        if (flag) {
            logger.info("初始化应用连接");
            flag = initServerConn();
        }

        return flag;
    }

    public boolean reload() {

        logger.info("重新初始化服务器、应用、服务器连接。");

        return init();

    }

    public void begin() {

        logger.info("监控开始........");
        Thread t = new Thread(new InnerMonitor());
        t.start();

    }

    private boolean initServerConn() {

        Set<Entry<String, Server>> set = serverServerMap.entrySet();
        for (Entry<String, Server> entry : set) {
            Server server = entry.getValue();
            String nid = server.getNid();
            Connection conn;
            if (serverConnMap.containsKey(nid)) {
                conn = serverConnMap.get(nid);
                if (conn != null)
                    conn.close();
            }
            conn = getConn(server);
            serverConnMap.put(nid, conn);
        }

        ICache cache = ShareCache.getInstance();
        cache.addCacheData(Consts.SERVID_CONN_MAP, serverConnMap);

        return true;

    }

    private Connection getConn(Server server) {
        String ip = server.getCip();
        String username = server.getUser();
        String password = server.getPasswd();
        Connection conn = null;
        try {

            conn = new Connection(ip);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username,
                password);

            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");

        } catch (IOException e) {
            logger.error("监控线程 连接服务器(" + ip + ")时，用户密码验证失败", e);
        }
        return conn;
    }

    @SuppressWarnings("unchecked")
    private boolean initServAndApp() {
        boolean flag;
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read("../monitor/server.xml");
            Element root = doc.getRootElement();
            List<Element> servsE = root.elements();
            for (Element servE : servsE) {

                initServ(servE);

                String serverid = servE.elementText("nid");
                Element approot = servE.element("apps");
                List<Element> appsE = approot.elements();
                for (Element appE : appsE) {
                    initApp(serverid, appE);
                }
            }

            ICache cache = ShareCache.getInstance();
            cache.addCacheData(Consts.SERVID_SERVER_MAP, serverServerMap);
            cache.addCacheData(Consts.SERVID_APP_MAP, serverAppMap);
            cache.addCacheData(Consts.SERVER_LIST, serverList);
            cache.addCacheData(Consts.APP_LIST, appList);

            flag = true;
        } catch (DocumentException e) {
            logger.error(e.getMessage(), e);
            flag = false;
        }
        return flag;
    }

    private void initApp(String serverid, Element appE) {
        App app = new App();

        app.setNserverid(serverid);
        app.setNid(appE.elementText("nid"));
        app.setCname(appE.elementText("cname"));
        app.setCport(appE.elementText("cport"));
        app.setCstartcmd(appE.elementText("cstartcmd"));
        app.setCstopcmd(appE.elementText("cstopcmd"));
        app.setCrestartcmd(appE.elementText("crestartcmd"));

        if (serverAppMap.containsKey(serverid)) {
            serverAppMap.get(serverid).add(app);
        } else {
            List<App> listApp = new ArrayList<App>();
            listApp.add(app);
            serverAppMap.put(serverid, listApp);
        }
        appList.add(app);
    }

    private void initServ(Element servE) {
        Server server = new Server();
        server.setNid(servE.elementText("nid"));
        server.setCname(servE.elementText("cname"));
        server.setCip(servE.elementText("cip"));
        server.setUser(servE.elementText("user"));
        server.setPasswd(servE.elementText("passwd"));
        serverServerMap.put(server.getNid(), server);
        serverList.add(server);
    }

    private class InnerMonitor implements Runnable {

        private void heartbeatMonitor() {

            // TODO 监控线程数 需要提取配置项
            ExecutorService exeServ = Executors.newCachedThreadPool();

            int beatState = 1;
            while (true) {
                long start = System.currentTimeMillis();
                Set<Entry<String, Server>> set = serverServerMap.entrySet();

                final CountDownLatch latch = new CountDownLatch(
                        serverServerMap.size());

                for (Entry<String, Server> entry : set) {

                    Server server = entry.getValue();
                    List<App> appList = serverAppMap.get(server.getNid());

                    MonitorThread thread = new MonitorThread(server, appList,
                            latch, beatState, monitorDao);
                    exeServ.execute(thread);
                }

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    logger.error("监控线程latch.await()异常", e);
                }

                long tookTime = Util.getTime(start);
                long sleepTime = 10000 - tookTime; // TODO 30000 监控间隔，需要提取配置项
                try {
                    if (sleepTime > 0)
                        Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    logger.error("监控线程sleep异常", e);
                }
                beatState = beatState * -1;
            }
        }

        @Override
        public void run() {
            heartbeatMonitor();
        }

    }
}
