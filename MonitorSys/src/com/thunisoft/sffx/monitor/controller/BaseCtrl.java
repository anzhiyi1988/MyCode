package com.thunisoft.sffx.monitor.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseCtrl {

    private static final Logger logger = LoggerFactory
            .getLogger(BaseCtrl.class);

    protected void writeStr2Html(HttpServletResponse resp, String str) {
        PrintWriter writer = null;
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;UTF-8");
            writer = resp.getWriter();
            writer.print(str);
            writer.flush();
        } catch (Exception e) {
            logger.error("输出数据出错", e);
        } finally {
            writer.close();
        }
    }

}
