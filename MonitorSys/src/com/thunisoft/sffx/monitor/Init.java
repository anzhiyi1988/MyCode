package com.thunisoft.sffx.monitor;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Init {

    private static final Logger logger = LoggerFactory.getLogger(Init.class);

    static {
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read("./conf/server.xml");
            Element root = doc.getRootElement();

            List<Element> servs = root.elements();
            for (Element serv : servs) {
                String nid = serv.elementText("nid");
                String cname = serv.elementText("cname");
                String cip = serv.elementText("cip");
                String user = serv.elementText("user");
                String passwd = serv.elementText("passwd");
                Element approot = serv.element("apps");
                List<Element> apps = approot.elements();
                for (Element app : apps) {
                    String appnid = app.elementText("nid");
                    String appcname = app.elementText("cname");
                    String cport = app.elementText("cport");
                    String cstartcmd = app.elementText("cstartcmd");
                    String cstopcmd = app.elementText("cstopcmd");
                    String crestartcmd = app.elementText("crestartcmd");
                }
            }

        } catch (DocumentException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
