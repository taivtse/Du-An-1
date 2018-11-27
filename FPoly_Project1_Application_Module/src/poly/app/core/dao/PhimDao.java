/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.dao;

import java.util.List;
import poly.app.core.data.dao.GenericDao;
import poly.app.core.entities.Phim;

/**
 *
 * @author Light
 */
public interface PhimDao extends GenericDao<String, Phim>{
    public List<Phim> getPhimHienCo();
    public List<Phim> getPhimCoSuatChieuTrongNgay();
    public List<Phim> getPhimDangChieu();
}
