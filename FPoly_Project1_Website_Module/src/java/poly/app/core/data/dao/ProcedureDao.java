/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.app.core.data.dao;

import java.util.List;

/**
 *
 * @author vothanhtai
 */
public interface ProcedureDao{
//    public <POJO> List<POJO> execute(POJO pojo, Object... parameters);
    public List<Object[]> execute(String procedureName, Object... parameters);
}