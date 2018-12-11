package poly.app.core.daoimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poly.app.core.dao.VeBanDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.VeBan;

public class VeBanDaoImpl extends AbstractDao<String, VeBan> implements VeBanDao{

    @Override
    public List<VeBan> loadVeBanTheoNgay(Date date) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("ngayBan", date);
        return this.getByProperties(conditions);
    }
}