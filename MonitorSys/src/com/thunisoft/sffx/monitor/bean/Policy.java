package com.thunisoft.sffx.monitor.bean;

import java.util.Date;

public class Policy {

    private String nfkid; // foreign key id 服务器或者应用的id

    private String cqueue; // 重启队列

    private String npolicy; // 手动重启还是自动重启

    private Date djlsj; // 记录时间

    public String getNfkid() {
        return nfkid;
    }

    public void setNfkid(String nfkid) {
        this.nfkid = nfkid;
    }

    public String getCqueue() {
        return cqueue;
    }

    public void setCqueue(String cqueue) {
        this.cqueue = cqueue;
    }

    public String getNpolicy() {
        return npolicy;
    }

    public void setNpolicy(String npolicy) {
        this.npolicy = npolicy;
    }

    public Date getDjlsj() {
        return djlsj;
    }

    public void setDjlsj(Date djlsj) {
        this.djlsj = djlsj;
    }

}
