package poly.app.core.api;

import java.util.List;
import java.util.Map;
import javax.ws.rs.Path;
import poly.app.core.daoimpl.DinhDangPhimDaoImpl;
import poly.app.core.entities.DinhDangPhim;

@Path("dinhdangphim")
public class DinhDangPhimAPI extends DinhDangPhimDaoImpl{

    public List<DinhDangPhim> getAll() {
        
    }

    @Override
    public DinhDangPhim getById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DinhDangPhim> getByProperties(Map<String, Object> conditions, List<String> sortExpressions, String sortDirection, Integer offset, Integer limit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(DinhDangPhim entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DinhDangPhim entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(DinhDangPhim entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int multipleDelete(List<DinhDangPhim> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveOrUpdate(DinhDangPhim entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}