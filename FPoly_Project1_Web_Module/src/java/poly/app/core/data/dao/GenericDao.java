package poly.app.core.data.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;

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
    boolean insert(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(T entity) throws Exception;
    boolean deleteById(ID id) throws Exception;
    int multipleDelete(List<T> entities) throws Exception;
    boolean saveOrUpdate(T entity) throws Exception;
}