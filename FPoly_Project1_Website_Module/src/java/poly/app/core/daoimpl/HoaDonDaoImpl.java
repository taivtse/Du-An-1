package poly.app.core.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import poly.app.core.dao.HoaDonDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.HoaDon;

public class HoaDonDaoImpl extends AbstractDao<String, HoaDon> implements HoaDonDao{

    @Override
    public HoaDon getNewestHoaDon() {
        HoaDon result = null;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.addOrder(Order.desc("id"));
            cr.setMaxResults(1);
            result = (HoaDon) cr.uniqueResult();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return result;
    }
    
}