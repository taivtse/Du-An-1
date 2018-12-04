package poly.app.core.daoimpl;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import poly.app.core.dao.NguoiDungDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.NguoiDung;

public class NguoiDungDaoImpl extends AbstractDao<String, NguoiDung> implements NguoiDungDao{

    @Override
    public NguoiDung getByIdAndPassword(String id, String matKhau) {
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("id", id));
            cr.add(Restrictions.eq("matKhau", matKhau));
            return (NguoiDung) cr.uniqueResult();
        } catch (Exception ex) {
            throw ex;
        }finally{
            session.close();
        }
    }

    @Override
    public NguoiDung getBySoDienThoai(String soDienThoai) {
       Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("soDienThoai", soDienThoai));
            return (NguoiDung) cr.uniqueResult();
        } catch (Exception ex) {
            throw ex;
        }finally{
            session.close();
        }
    }
    
}