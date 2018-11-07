package poly.app.core.entities;
// Generated Nov 7, 2018 7:54:44 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * DoAn generated by hbm2java
 */
public class DoAn  implements java.io.Serializable {


     private String id;
     private LoaiDoAn loaiDoAn;
     private String ten;
     private boolean dangBan;
     private Set<DoAnChiTiet> doAnChiTiets = new HashSet<DoAnChiTiet>(0);

    public DoAn() {
    }

	
    public DoAn(String id, LoaiDoAn loaiDoAn, String ten, boolean dangBan) {
        this.id = id;
        this.loaiDoAn = loaiDoAn;
        this.ten = ten;
        this.dangBan = dangBan;
    }
    public DoAn(String id, LoaiDoAn loaiDoAn, String ten, boolean dangBan, Set<DoAnChiTiet> doAnChiTiets) {
       this.id = id;
       this.loaiDoAn = loaiDoAn;
       this.ten = ten;
       this.dangBan = dangBan;
       this.doAnChiTiets = doAnChiTiets;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public LoaiDoAn getLoaiDoAn() {
        return this.loaiDoAn;
    }
    
    public void setLoaiDoAn(LoaiDoAn loaiDoAn) {
        this.loaiDoAn = loaiDoAn;
    }
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    public Boolean isDangBan() {
        return this.dangBan;
    }
    
    public void setDangBan(boolean dangBan) {
        this.dangBan = dangBan;
    }
    public Set<DoAnChiTiet> getDoAnChiTiets() {
        return this.doAnChiTiets;
    }
    
    public void setDoAnChiTiets(Set<DoAnChiTiet> doAnChiTiets) {
        this.doAnChiTiets = doAnChiTiets;
    }




}


