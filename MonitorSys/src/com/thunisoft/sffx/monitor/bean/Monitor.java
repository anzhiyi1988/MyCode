package com.thunisoft.sffx.monitor.bean;

public class Monitor {

    private String nfkid; // foreign key id 服务器或者应用的id

    private String monitortime;

    private String isalive;

    public String getNfkid() {
        return nfkid;
    }

    public void setNfkid(String nfkid) {
        this.nfkid = nfkid;
    }

    public String getMonitortime() {
        return monitortime;
    }

    public void setMonitortime(String monitortime) {
        this.monitortime = monitortime;
    }

    public String getIsalive() {
        return isalive;
    }

    public void setIsalive(String isalive) {
        this.isalive = isalive;
    }

}
