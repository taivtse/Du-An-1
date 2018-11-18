package poly.app.core.daoimpl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import poly.app.core.dao.GheNgoiDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.GheNgoi;
import poly.app.core.entities.PhongChieu;

public class GheNgoiDaoImpl extends AbstractDao<Integer, GheNgoi> implements GheNgoiDao{

    @Override
    public List<GheNgoi> getGheNgoiByPhongChieu(PhongChieu phongChieu) {
        List<GheNgoi> list;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            cr.add(Restrictions.eq("phongChieu", phongChieu));
            
            list = cr.list();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return list;
    }
    
}