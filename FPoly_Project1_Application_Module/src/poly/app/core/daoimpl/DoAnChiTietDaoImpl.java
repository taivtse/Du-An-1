package poly.app.core.daoimpl;

import java.util.ArrayList;
import java.util.List;
import poly.app.core.common.CoreConstant;
import poly.app.core.dao.DoAnChiTietDao;
import poly.app.core.data.daoimpl.AbstractDao;
import poly.app.core.entities.DoAnChiTiet;

public class DoAnChiTietDaoImpl extends AbstractDao<Integer, DoAnChiTiet> implements DoAnChiTietDao{
    @Override
    public List<DoAnChiTiet> getAllAndOrderBySize(){
        List<String> sortExpression = new ArrayList<String>();
        sortExpression.add("doAn");
        sortExpression.add("kichCoDoAn");
        
        return this.getByProperties(null, sortExpression, CoreConstant.SORT_ASC, null, null);
    }
}