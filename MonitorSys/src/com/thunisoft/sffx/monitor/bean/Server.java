package com.thunisoft.sffx.monitor.bean;

import java.util.Date;

public class Server {

    private String nid; // 服务器ID

    private String cname; // 服务器名称：hostname

    private String cip; // IP

    private String user;

    private String passwd;

    private String cstartcmd;

    private String cstopcmd;

    private String crestartcmd;

    private String cmonitorcmd;

    private Date djlsj; // 记录时间

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCstartcmd() {
        return cstartcmd;
    }

    public void setCstartcmd(String cstartcmd) {
        this.cstartcmd = cstartcmd;
    }

    public String getCstopcmd() {
        return cstopcmd;
    }

    public void setCstopcmd(String cstopcmd) {
        this.cstopcmd = cstopcmd;
    }

    public String getCrestartcmd() {
        return crestartcmd;
    }

    public void setCrestartcmd(String crestartcmd) {
        this.crestartcmd = crestartcmd;
    }

    public String getCmonitorcmd() {
        return cmonitorcmd;
    }

    public void setCmonitorcmd(String cmonitorcmd) {
        this.cmonitorcmd = cmonitorcmd;
    }

    public Date getDjlsj() {
        return djlsj;
    }

    public void setDjlsj(Date djlsj) {
        this.djlsj = djlsj;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}