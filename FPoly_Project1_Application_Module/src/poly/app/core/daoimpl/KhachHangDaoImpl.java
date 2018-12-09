package poly.app.core.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poly.app.core.dao.KhachHangDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.KhachHang;

public class KhachHangDaoImpl extends AbstractDao<String, KhachHang> implements KhachHangDao {

    @Override
    public KhachHang getBySoDienThoai(String soDienThoai) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("soDienThoai", soDienThoai);
        List<KhachHang> list = this.getByProperties(conditions);
        
        if (list.size() == 0) {
            return null;
        }

        return list.get(0);
    }
}
