/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.dao;

import java.util.List;
import poly.app.core.data.dao.GenericDao;
import poly.app.core.entities.DoAn;
import poly.app.core.entities.DoAnChiTiet;

/**
 *
 * @author vothanhtai
 */
public interface DoAnChiTietDao extends GenericDao<Integer, DoAnChiTiet>{
    public List<DoAnChiTiet> getAllAndOrderBySize();
    public List<DoAnChiTiet> getByDoAn(DoAn doAn);
}
