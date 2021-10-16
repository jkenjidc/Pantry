package sample;

public class brandTable {
    String bid,bname,org;
    public brandTable(String b,String n,String o){
        bid=b;
        bname=n;
        org=o;
    }
    public String getBid(){ return bid;}
    public String getBname(){ return bname;}
    public String getOrg(){ return org;}
}
