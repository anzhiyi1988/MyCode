package com.thunisoft.sffx.monitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thunisoft.sffx.monitor.Consts;
import com.thunisoft.sffx.monitor.monitor.MonitorSchedul;
import com.thunisoft.sffx.monitor.service.MonitorService;
import com.thunisoft.sffx.monitor.util.Util;

@Controller
@RequestMapping(value = "/monitor")
public class MonitorCtrl extends BaseCtrl {

    @Autowired
    @Qualifier("monitorService")
    private MonitorService service;

    @Autowired
    @Qualifier("monitorSchedul")
    private MonitorSchedul monitorSchedul;

    @RequestMapping(value = "/list/id", method = RequestMethod.GET)
    public String getMonitorList(@RequestParam String fkid,
            HttpServletResponse resp) {
        List<String> list = service.getMonitorList(Consts.MONITOR_LIST + fkid);

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> xaxis = new ArrayList<String>();
        List<String> series = new ArrayList<String>();
        for (String json : list) {
            JSONObject obj = Util.ojb2Json(json);
            xaxis.add(obj.getString("monitortime"));
            series.add(obj.getString("isalive"));
        }
        map.put("xaxis", xaxis);
        map.put("series", series);

        String str = Util.toJsonStr(map);
        writeStr2Html(resp, str);
        return null;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletResponse resp) {
        boolean flag = monitorSchedul.init();
        if (flag) {
            writeStr2Html(resp, "success");
        } else {
            writeStr2Html(resp, "failed");
        }
        return null;

    }

    @RequestMapping(value = "/reload", method = RequestMethod.GET)
    public String reload(HttpServletResponse resp) {
        boolean flag = monitorSchedul.reload();

        if (flag) {
            writeStr2Html(resp, "success");
        } else {
            writeStr2Html(resp, "failed");
        }
        return null;
    }

    @RequestMapping(value = "/begin", method = RequestMethod.GET)
    public String begin(HttpServletResponse resp) {
        monitorSchedul.begin();
        writeStr2Html(resp, "start monitor ...");
        return null;
    }
}
