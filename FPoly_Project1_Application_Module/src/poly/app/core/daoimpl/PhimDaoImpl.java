package poly.app.core.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poly.app.core.common.PhimDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.Phim;

public class PhimDaoImpl extends AbstractDao<String, Phim> implements PhimDao{
    @Override
    public List<Phim> getPhimHienCo() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("daXoa", false);
        return this.getByProperties(conditions);
    }
}