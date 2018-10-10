package orz.anzhy.sffx.monitor.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import orz.anzhy.sffx.monitor.Consts;
import orz.anzhy.sffx.monitor.bean.App;
import orz.anzhy.sffx.monitor.bean.Monitor;
import orz.anzhy.sffx.monitor.bean.Server;
import orz.anzhy.sffx.monitor.cache.ShareCache;
import orz.anzhy.sffx.monitor.dao.MonitorDao;

public class MonitorThread implements Runnable {

    private static final Logger logger = LoggerFactory
            .getLogger(MonitorThread.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private Server server;

    private List<App> appList;

    private CountDownLatch latch;

    private int beatState;

    private MonitorDao monitorDao;

    public MonitorThread(Server server, List<App> appList,
            CountDownLatch latch, int beatState, MonitorDao monitorDao) {
        this.server = server;
        this.appList = appList;
        this.latch = latch;
        this.beatState = beatState;
        this.monitorDao = monitorDao;
    }

    @Override
    public void run() {
        monitorServer();
        monitorApp();
        latch.countDown();
    }

    private void monitorServer() {
        @SuppressWarnings("unchecked")
        Map<String, Connection> serverConnMap = (Map<String, Connection>) ShareCache
                .getInstance().getCacheData(Consts.SERVID_CONN_MAP);
        Connection conn = serverConnMap.get(server.getNid());
        try {
            String command = server.getCmonitorcmd();
            boolean isRun = isNormal(conn, command);
            if (isRun) {
                addMonitor(server.getNid(), beatState);
            } else {
                addMonitor(server.getNid(), 0);
            }
        } catch (IOException e) {
            logger.error("读取服务器返回状态信息时，错误", e);
            addMonitor(server.getNid(), 0);
        } catch (IllegalStateException e) {
            logger.error("cannot open session!!", e);
            addMonitor(server.getNid(), 0);
        }
    }

    private void monitorApp() {

        @SuppressWarnings("unchecked")
        Map<String, Connection> serverConnMap = (Map<String, Connection>) ShareCache
                .getInstance().getCacheData(Consts.SERVID_CONN_MAP);

        Connection conn = serverConnMap.get(server.getNid());

        for (App app : appList) {
            try {
                String command = app.getCmonitorcmd();
                boolean isNormal = isNormal(conn, command);
                if (isNormal) {
                    addMonitor(app.getNid(), beatState);
                } else {
                    addMonitor(app.getNid(), 0);
                }
            } catch (IOException e) {
                logger.error("读取服务器返回状态信息时，错误", e);
                addMonitor(app.getNid(), 0);
            } catch (IllegalStateException e) {
                logger.error("cannot open session!!", e);
                addMonitor(app.getNid(), 0);
            }
        }
    }

    private boolean isNormal(Connection conn, String command) throws IOException {
        Session session = conn.openSession();
        session.execCommand(command);
        InputStream is = new StreamGobbler(session.getStdout());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            logger.info(line);
        }

        br.close();
        session.close();
        // TODO 补全判断 并发送短信
        return true;
    }

    private void addMonitor(final String nid, final int beatState) {
        String listName = Consts.MONITOR_LIST + nid;
        Monitor monitor = new Monitor();
        monitor.setNfkid(nid);
        monitor.setMonitortime(sdf.format(new Date()));
        monitor.setIsalive(String.valueOf(beatState));
        monitorDao.addMonitor(listName, monitor);
    }
}
