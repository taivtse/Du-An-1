package poly.app.core.data.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import poly.app.core.common.CoreConstant;
import poly.app.core.data.dao.GenericDao;
import poly.app.core.utils.HibernateUtil;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {

    private Class<T> persistenceClass;

    public AbstractDao() {
        // generic < x , y > as array
        // set persistenceClass = T
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public Class getPersistenceClass() {
        return this.persistenceClass;
    }

    public String getPersistenceClassName() {
        return this.persistenceClass.getSimpleName();
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<T> getAll() {
        List<T> list = null;
        Session session = this.getSession();
        try {
            list = session.createCriteria(this.getPersistenceClass()).list();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public T getById(ID id) {
        T result = null;
        Session session = this.getSession();
        try {
            // note: the first parameter is class type, so we pass persistenceClass at this situation
            result = (T) session.get(this.getPersistenceClass(), id);
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<T> getByProperties(Map<String, Object> conditions, List<String> sortExpressions, String sortDirection, Integer offset, Integer limit) {
        List<T> list;
        Session session = this.getSession();
        try {
            Criteria cr = session.createCriteria(this.getPersistenceClass());
            if (conditions != null) {
                for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                    cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                }
            }

            if (sortExpressions != null && sortDirection != null) {
                for (String sortExpression : sortExpressions) {
                    Order order = sortDirection.equalsIgnoreCase(CoreConstant.SORT_ASC)
                        ? Order.asc(sortExpression) : Order.desc(sortExpression);
                cr.addOrder(order);
                }
            }

//            set start position offset
            if (offset != null && offset >= 0) {
                cr.setFirstResult(offset);
            }
//            set limit row
            if (limit != null && limit > 0) {
                cr.setMaxResults(limit);
            }

            list = cr.list();
        } catch (HibernateException ex) {
            throw ex;
        } finally {
            session.close();
        }
        return list;
    }
    
    public List<T> getByProperties(Map<String, Object> conditions){
        return this.getByProperties(conditions, null, null, null, null);
    }

    @Override
    public boolean insert(T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            T mappedEntity = (T) session.merge(entity);
            session.delete(mappedEntity);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteById(ID id) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            T entity = (T) session.get(this.getPersistenceClass(), id);
            T mappedEntity = (T) session.merge(entity);
            session.delete(mappedEntity);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public int multipleDelete(List<T> entities) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int countDeleted = 0;
        try {
            for (T entity : entities) {
                session.delete(entity);
                countDeleted++;
            }
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
        return countDeleted;
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }
}
