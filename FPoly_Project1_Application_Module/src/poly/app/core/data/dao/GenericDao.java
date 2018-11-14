package poly.app.core.data.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vothanhtai
 */
public interface GenericDao <ID extends Serializable, T>{
    List<T> getAll();
    T getById(ID id);
    public List<T> getByProperties(Map<String, Object> conditions, List<String> sortExpressions, String sortDirection, Integer offset, Integer limit);
    boolean insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    boolean deleteById(ID id);
    int multipleDelete(List<T> entities);
    boolean saveOrUpdate(T entity);
}