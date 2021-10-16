package sample;



public class groupTable {
    String gid,gname,fats,carbs,vitamins;
    public groupTable(String i, String n, String f,String c, String v){
        gid=i;
        gname=n;
        fats=f;
        carbs=c;
        vitamins=v;
    }

    public String getGid() {
        return gid;
    }
    public String getCarbs() {
        return carbs;
    }
    public String getGname() {
        return gname;
    }
    public String getFats() {
        return fats;
    }
    public String getVitamins() { return vitamins; }

    public void setGname(String hel) {
        this.gname = hel;
    }

    public void setCarbs(String cal) {
        this.carbs = cal;
    }
    public void setFats(String cal) {
        this.fats = cal;
    }
    public void setVitamins(String cal) {
        this.vitamins = cal;
    }
}
