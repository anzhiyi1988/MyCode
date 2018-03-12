package com.thunisoft.sffx.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.thunisoft.sffx.monitor.bean.Monitor;
import com.thunisoft.sffx.monitor.dao.MonitorDao;

@Service
public class MonitorService {

    @Autowired
    @Qualifier("monitorDao")
    MonitorDao dao;

    public List<String> getMonitorList(String listName) {

        List<String> list = dao.getMonitorList(listName);
        return list;

    }

    public int addMonitor(String listName, Monitor monitor) {
        int result = dao.addMonitor(listName, monitor);
        return result;
    }

}
