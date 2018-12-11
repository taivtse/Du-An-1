package poly.app.core.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.DoAn;

public class DoAnDaoImpl extends AbstractDao<String, DoAn> {

    @Override
    public List<DoAn> getAll() {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("daXoa", false);
        return this.getByProperties(conditions);
    }

}
