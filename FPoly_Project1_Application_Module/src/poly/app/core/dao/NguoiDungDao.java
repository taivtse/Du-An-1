/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.dao;

import poly.app.core.data.dao.GenericDao;
import poly.app.core.entities.NguoiDung;

/**
 *
 * @author vothanhtai
 */
public interface NguoiDungDao extends GenericDao<String, NguoiDung>{
    public NguoiDung getByIdAndPassword(String id, String matKhau);
}
