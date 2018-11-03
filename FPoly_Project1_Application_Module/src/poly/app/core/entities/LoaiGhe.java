package poly.app.core.entities;
// Generated Nov 3, 2018 10:13:24 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * LoaiGhe generated by hbm2java
 */
public class LoaiGhe  implements java.io.Serializable {


     private String id;
     private String tenGhe;
     private int phuThu;
     private Set<GheNgoi> gheNgois = new HashSet<GheNgoi>(0);

    public LoaiGhe() {
    }

	
    public LoaiGhe(String id, String tenGhe, int phuThu) {
        this.id = id;
        this.tenGhe = tenGhe;
        this.phuThu = phuThu;
    }
    public LoaiGhe(String id, String tenGhe, int phuThu, Set<GheNgoi> gheNgois) {
       this.id = id;
       this.tenGhe = tenGhe;
       this.phuThu = phuThu;
       this.gheNgois = gheNgois;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getTenGhe() {
        return this.tenGhe;
    }
    
    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }
    public int getPhuThu() {
        return this.phuThu;
    }
    
    public void setPhuThu(int phuThu) {
        this.phuThu = phuThu;
    }
    public Set<GheNgoi> getGheNgois() {
        return this.gheNgois;
    }
    
    public void setGheNgois(Set<GheNgoi> gheNgois) {
        this.gheNgois = gheNgois;
    }




}


