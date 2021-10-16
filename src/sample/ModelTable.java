package sample;



public class ModelTable {
    String fid,fname,cal,ser,gid,bid;
    public ModelTable(String i, String n, String c,String s, String g, String b){
        fid=i;
        cal=c;
        fname=n;
        ser=s;
        gid=g;
        bid=b;
    }

    public String getFid() {
        return fid;
    }
    public String getCal() {
        return cal;
    }
    public String getFname() {
        return fname;
    }
    public String getSer() {
        return ser;
    }
    public String getGid() { return gid; }
    public String getBid() { return bid; }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setFname(String hel) {
        this.fname = hel;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }
    public void setSer(String cal) {
        this.ser = cal;
    }
    public void setGid(String cal) {
        this.gid = cal;
    }
    public void setBid(String cal) {
        this.bid = cal;
    }
}
