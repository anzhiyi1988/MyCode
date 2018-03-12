package com.thunisoft.sffx.monitor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thunisoft.sffx.monitor.bean.Server;
import com.thunisoft.sffx.monitor.service.ServerService;
import com.thunisoft.sffx.monitor.util.Util;

@Controller
@RequestMapping(value = "/serv")
public class ServerCtrl extends BaseCtrl {

    private static final Logger logger = LoggerFactory
            .getLogger(ServerCtrl.class);

    @Autowired
    @Qualifier("serverService")
    private ServerService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getServList(HttpServletRequest req, HttpServletResponse resp) {

        long start = System.currentTimeMillis();
        List<Server> list = service.getServList();

        if (list == null) {
            writeStr2Html(resp, "获取服务列表为空！！");
            return null;
        }

        logger.info("物理设备列表查询时间：" + Util.getTime(start) + "，数量：" + list.size()
                + "。");

        String str = Util.toJsonStr(list);
        writeStr2Html(resp, str);
        return null;
    }

}
