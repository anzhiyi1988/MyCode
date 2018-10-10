package orz.anzhy.sffx.monitor.bean;

import java.util.Date;

public class App {

    private String nserverid;// 服务器ID

    private String nid;// 应用ID

    private String cname;// 应用名称

    private String cport;

    private String napptype;

    private String cstartcmd;

    private String cstopcmd;

    private String crestartcmd;

    private String cmonitorcmd;

    private Date djlsj; // 记录时间

    public String getNserverid() {
        return nserverid;
    }

    public void setNserverid(String nserverid) {
        this.nserverid = nserverid;
    }

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

    public String getNapptype() {
        return napptype;
    }

    public void setNapptype(String napptype) {
        this.napptype = napptype;
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

    public String getCport() {
        return cport;
    }

    public void setCport(String cport) {
        this.cport = cport;
    }
}