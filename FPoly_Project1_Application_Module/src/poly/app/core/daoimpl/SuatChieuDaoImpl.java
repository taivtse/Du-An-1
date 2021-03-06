package poly.app.core.daoimpl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import poly.app.core.dao.SuatChieuDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.Phim;
import poly.app.core.entities.PhongChieu;
import poly.app.core.entities.SuatChieu;
import poly.app.core.helper.DateHelper;

public class SuatChieuDaoImpl extends AbstractDao<String, SuatChieu> implements SuatChieuDao {

    @Override
    public List<SuatChieu> getSuatChieuHienTaiByPhim(Phim phim) {
        List<SuatChieu> list;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("phim", phim));
            cr.add(Restrictions.between("ngayChieu", new Date(), DateHelper.rollDays(new Date(), 1)));
            cr.addOrder(Order.asc("gioBatDau"));
            

            list = cr.list();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return list;
    }
    
    @Override
    public List<SuatChieu> getSuatChieuByNgayVaByPhong(Date date, PhongChieu phongChieu) {
        List<SuatChieu> list;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("ngayChieu", date));
            cr.add(Restrictions.eq("phongChieu", phongChieu));
            cr.addOrder(Order.asc("gioBatDau"));
            list = cr.list();
        } catch (HibernateException e) {
            throw e;
        }
        return list;
    }

    @Override
    public boolean updateAnhHuongSuatChieuSau(SuatChieu suatChieu, int durationChange) {
        try {
            this.update(suatChieu);
            updateThoiGianCacSuatChieu(suatChieu, durationChange);
        } catch (HibernateException e) {
            throw e;
        }
        return true;
    }

    @Override
    public void updateThoiGianCacSuatChieu(SuatChieu suatChieu, int durationChange){
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "UPDATE suat_chieu SET gio_bat_dau = gio_bat_dau + INTERVAL :durationChange MINUTE, "
                    + "gio_ket_thuc = gio_ket_thuc + INTERVAL :durationChange MINUTE "
                    + "WHERE ngay_chieu = :ngayChieu "
                    + "AND TIME( gio_bat_dau ) > TIME( :startTime ) AND phong_chieu_id = :phongChieuId";
            int phongChieuId = suatChieu.getPhongChieu().getId();
            Date startTime = suatChieu.getGioBatDau();
            Date ngayChieu = suatChieu.getNgayChieu();
            
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("durationChange", durationChange);
            query.setParameter("phongChieuId", phongChieuId);
            query.setParameter("startTime", startTime);
            query.setParameter("ngayChieu", ngayChieu);
            
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
}
