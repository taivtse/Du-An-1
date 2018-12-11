package poly.app.core.data.daoimpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import poly.app.core.data.dao.ProcedureDao;
import poly.app.core.utils.HibernateUtil;

public class ProcedureDaoImpl implements ProcedureDao{
    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Object[]> execute(String procedureName, Object... parameters) {
        List<Object[]> list = null;
        Session session = this.getSession();
        try {
//            Build call query
            StringBuilder builder = new StringBuilder("{CALL ");
            builder.append(procedureName);
            builder.append("(");
            String[] chars = new String[parameters.length];
            Arrays.fill(chars, "?");
            String requestInput = new String(String.join(",", chars));
            builder.append(requestInput);
            builder.append(")}");

            Query query = session.createSQLQuery(builder.toString());

//            Set parameter to query
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i, parameters[i]);
            }

//            get result list
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return list;
    }
}