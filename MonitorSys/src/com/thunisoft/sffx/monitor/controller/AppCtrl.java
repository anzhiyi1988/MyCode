package com.thunisoft.sffx.monitor.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thunisoft.sffx.monitor.bean.App;
import com.thunisoft.sffx.monitor.service.AppService;
import com.thunisoft.sffx.monitor.util.Util;

@Controller
@RequestMapping(value = "/app")
public class AppCtrl extends BaseCtrl {

    private static final Logger logger = LoggerFactory.getLogger(AppCtrl.class);

    @Autowired
    @Qualifier("appService")
    private AppService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAppList(HttpServletResponse resp) {
        long start = System.currentTimeMillis();
        List<App> list = service.getAppList();

        if (list == null) {
            writeStr2Html(resp, "获取应用列表为空！！");
            return null;
        }

        logger.info("物理设备列表查询时间：" + Util.getTime(start) + "，数量：" + list.size()
                + "。");

        String str = Util.toJsonStr(list);
        writeStr2Html(resp, str);
        return null;
    }

}
