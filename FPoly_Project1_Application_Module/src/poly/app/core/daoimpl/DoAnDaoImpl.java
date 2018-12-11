package poly.app.core.daoimpl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.DoAn;

public class DoAnDaoImpl extends AbstractDao<String, DoAn> {

    @Override
    public List<DoAn> getAll() {
        List<DoAn> list;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("daXoa", false));

            list = cr.list();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return list;
    }

}
