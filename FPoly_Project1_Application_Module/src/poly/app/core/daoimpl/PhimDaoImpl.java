package poly.app.core.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import poly.app.core.dao.PhimDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.Phim;

public class PhimDaoImpl extends AbstractDao<String, Phim> implements PhimDao{
    @Override
    public List<Phim> getPhimHienCo() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("daXoa", false);
        return this.getByProperties(conditions);
    }

    @Override
    public List<Phim> getPhimDangChieu() {
        List<Phim> list;
        Session session = this.getSession();
        try {
            Query query = session.createQuery("FROM Phim WHERE id IN(SELECT DISTINCT phim FROM SuatChieu WHERE ngayChieu = CURRENT_DATE)");
            list = query.list();
        } catch (HibernateException e) {
            throw e;
        }finally{
            session.close();
        }
        return list;
    }
}