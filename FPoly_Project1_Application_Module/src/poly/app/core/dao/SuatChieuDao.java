/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.dao;

import java.util.Date;
import java.util.List;
import poly.app.core.data.dao.GenericDao;
import poly.app.core.entities.Phim;
import poly.app.core.entities.PhongChieu;
import poly.app.core.entities.SuatChieu;

/**
 *
 * @author vothanhtai
 */
public interface SuatChieuDao extends GenericDao<String, SuatChieu>{
    public List<SuatChieu> getSuatChieuHienTaiByPhim(Phim phim);
    public List<SuatChieu> getSuatChieuByNgayVaByPhong(Date date, PhongChieu phongChieu);
    public boolean updateAnhHuongSuatChieuSau(SuatChieu suatChieu, int durationChange);
    public void updateThoiGianCacSuatChieu(SuatChieu suatChieu, int durationChange);
}
