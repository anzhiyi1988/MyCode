package com.thunisoft.down;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMain {

    public static List<String> pathList = new ArrayList<String>();

    public static Logger logger = LoggerFactory.getLogger(MyMain.class);

    public void download(String path) throws IOException {

        long start = System.currentTimeMillis();

        URL url = new URL(path);
        String fullpath = url.getFile();
        int last = fullpath.lastIndexOf("/");
        String dir = fullpath.substring(0, last);
        String savePath = "E:" + dir;
        String filename = fullpath.substring(last + 1);
        //        logger.debug(fullpath);
        //        logger.debug(dir);
        //        logger.debug(filename);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = conn.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        byte[] getData = bos.toByteArray();
        int size = bos.size();
        bos.close();

        File file = new File(saveDir + File.separator + filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        long end = System.currentTimeMillis();

        String msg = "下载文件完毕,用时：【" + (end - start) / 1000 + " s】，文件名：【"
                + filename + "】，文件大小：【" + size / 1000 + " kb】";
        logger.info(msg);
    }

    public void operDir(String urlStr) {

        if (isFile(urlStr)) {
            pathList.add(urlStr);
            logger.info(urlStr);
            return;
        }

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlStr);
            is = url.openStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                str = str.replace(" ", "");
                if (str.contains("<td><ahref=\"http://repo.thunisoft.com")) {
                    String[] arr = str.split("\"");
                    String fianlpath = arr[1];
                    if (isFile(fianlpath)) {
                        pathList.add(fianlpath);
                        logger.info(fianlpath);
                    } else {
                        operDir(fianlpath);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("深度遍历失败:" + urlStr, e);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                logger.error("error", e);
            }
        }

    }

    private boolean isFile(String path) {
        if (path.endsWith(".jar"))
            return true;
        if (path.endsWith(".pom"))
            return true;
        if (path.endsWith(".sha1"))
            return true;
        if (path.endsWith(".md5"))
            return true;
        return false;
    }

    public static void main(String[] args) {
        MyMain pro = new MyMain();
        List<String> list = pro.getAPPUrl();
// TODO ANZHY
        int zcount = list.size();
        int zcurr = 1;
        for (String rulStr : list) {
            pro.operDir(rulStr);
            int count = pathList.size();
            int curr = 1;
            for (String path : pathList) {
                logger.info("正在下载：" + curr + "/" + count);
                try {
                    pro.download(path);
                    logger.info("下载完毕：" + curr + "/" + count);
                } catch (Exception e) {
                    logger.error("下载失败：" + curr + "/" + count + ":" + path, e);
                }
                curr++;
            }
            logger.info("总下载进度完成：" + zcurr + "/" + zcount);
            zcurr++;
            pathList.clear();
        }
        System.out.println("finish");
    }
    
    private List<String> getMybatisUrl() {
        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/org/mybatis");
        return list;
    }

    private List<String> getArteryUrl() {
        List<String> list = new ArrayList<String>();
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/5.0.45");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/100.1.31");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/100.1.32");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/100.1.33");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/100.1.34");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/100.1.50");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/100.1.51");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/200.1.2");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.0");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.10");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.11");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.11000003");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.20");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.21");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.49");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.58");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.61");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/4.4.9");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/5.0.11111111");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/5.0.11111112");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/artery/artery-platform/5.0.23");
        return list;
    }

    private List<String> getCocallUrl() {
        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/cocall/cocall-open-client");
        return list;
    }
    
    private List<String> getAPPUrl() {
        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/com/thunisoft/AAP");
        return list;
    }
    
    
    private List<String> getMavenUrl() {
        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/org/apache/maven/plugins/maven-war-plugin");
        return list;
    }

    private List<String> getJacksonUrl() {
        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/com/fasterxml/jackson/core");
        return list;
    }

    private List<String> getOrgJacksonUrl() {
        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/jackson");
        return list;
    }

    private List<String> getAntlrUrl() {
        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/antlr/antlr");
        return list;
    }

    private List<String> getPlexusUrl() {
        //        List<String> list = getNextUrl("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus");
        List<String> list = new ArrayList<String>();
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/com/fasterxml/jackson/core/jackson-databind/2.2.2/jackson-databind-2.2.2-sources.jar.sha1");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus-active-collections/1.0-beta-2/plexus-active-collections-1.0-beta-2.jar.md5");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus-archiver/2.5/plexus-archiver-2.5.jar.sha1");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-6/plexus-interactivity-api-1.0-alpha-6.pom.md5");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus-maven-plugin/1.3.8/plexus-maven-plugin-1.3.8.jar");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus-tools/1.0.8/plexus-tools-1.0.8.pom.sha1");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus-utils/");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus-velocity/");
        list.add("http://repo.thunisoft.com/maven2/content/groups/public/org/codehaus/plexus/plexus/");

        return list;
    }

    private List<String> getFelixUrl() {
        List<String> list = new ArrayList<String>();
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.main");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.metatype");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.scr.annotations");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.scr");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.shell.tui");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.shell");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.utils");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.webconsole.plugins.event");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.webconsole.plugins.memoryusage");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.webconsole.plugins.scriptconsole");
        //        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.apache.felix.webconsole");
        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.osgi.compendium");
        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.osgi.core");
        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.osgi.foundation");
        list.add("http://repo.thunisoft.com/maven2/content/repositories/central/org/apache/felix/org.osgi.service.obr");
        return list;
    }

    private List<String> getNextUrl(String urlStr) {
        List<String> list = new ArrayList<String>();

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlStr);
            is = url.openStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                str = str.replace(" ", "");
                if (str.contains("<td><ahref=\"http://repo.thunisoft.com")) {
                    String[] arr = str.split("\"");
                    String fianlpath = arr[1];
                    list.add(fianlpath);
                }
            }
        } catch (Exception e) {
            logger.error("获取下一级失败：" + urlStr, e);

        } finally {
            try {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;

    }

}
