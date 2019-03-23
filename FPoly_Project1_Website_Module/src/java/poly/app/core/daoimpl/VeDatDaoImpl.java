package poly.app.core.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import poly.app.core.dao.VeDatDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.VeDat;

public class VeDatDaoImpl extends AbstractDao<String, VeDat> implements VeDatDao{
    @Override
    public VeDat getNewestVeDat() {
        VeDat result = null;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.addOrder(Order.desc("id"));
            cr.setMaxResults(1);
            result = (VeDat) cr.uniqueResult();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return result;
    }
}