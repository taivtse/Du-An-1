package poly.app.core.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import poly.app.core.dao.KhachHangDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.KhachHang;

public class KhachHangDaoImpl extends AbstractDao<String, KhachHang> implements KhachHangDao {

    @Override
    public KhachHang getBySoDienThoai(String soDienThoai) {
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("soDienThoai", soDienThoai));
            return (KhachHang) cr.uniqueResult();
        } catch (Exception ex) {
            throw ex;
        }finally{
            session.close();
        }
    }
}
