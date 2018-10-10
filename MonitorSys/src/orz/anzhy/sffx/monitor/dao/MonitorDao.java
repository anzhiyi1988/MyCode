package orz.anzhy.sffx.monitor.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import orz.anzhy.sffx.monitor.bean.Monitor;
import orz.anzhy.sffx.monitor.util.Util;

@Repository
public class MonitorDao extends BaseDao {

    public List<String> getMonitorList(String listName) {

        List<String> listJson = listAll(listName);

        return listJson;
    }

    public int addMonitor(String listName, Monitor monitor) {
        Long l = listAdd(listName, Util.obj2JsonStr(monitor));
        if (l == null) {
            return 0; // 失败
        }
        return 1;
    }

}
