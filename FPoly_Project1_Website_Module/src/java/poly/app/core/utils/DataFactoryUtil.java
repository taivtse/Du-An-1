package poly.app.core.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataFactoryUtil {

    public static <T> Vector objectToVector(T object) throws Exception{
        Vector result = new Vector();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        try {
            for (Field field : fields) {
//                Get current field
                Field curField = clazz.getDeclaredField(field.getName());
 //                Set can accesible this field
                curField.setAccessible(true);
 //                Get and add value to vector
                result.add(curField.get(object));
 //                Set can not accesible this field
                curField.setAccessible(false);
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            throw ex;
        }
        return result;
    }

    public static <T> Vector objectToVectorByFields(T object, String[] objectFeilds) throws Exception {
        Vector result = new Vector();
        Class<?> clazz = object.getClass();
        
        try {
            for (String fieldName : objectFeilds) {
                Field curField = clazz.getDeclaredField(fieldName);
                curField.setAccessible(true);
                result.add(curField.get(object));
                curField.setAccessible(false);
            }
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException ex) {
            throw ex;
        }

        return result;
    }

    public static <T> Vector objectListToVectorByFields(List<T> list, String[] objectFeilds) throws Exception{
        Vector result = new Vector();
        for (T t : list) {
            try {
                result.add(DataFactoryUtil.objectToVectorByFields(t, objectFeilds));
            } catch (Exception ex) {
                throw ex;
            }
        }
        return result;
    }
    
    public static <T> T mergeTwoObject(T objectReusult, T objectInfo) throws Exception{
        Class<?> clazz = objectReusult.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        try {
            for (Field field : fields) {
//                Get current field
                Field curField = clazz.getDeclaredField(field.getName());
 //                Set can accesible this field
                curField.setAccessible(true);
 
//                Get and value
                if (curField.get(objectInfo) != null) {
                    
                    curField.set(objectReusult, curField.get(objectInfo));
                }
                
 //                Set can not accesible this field
                curField.setAccessible(false);
            }
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            throw ex;
        }
        return objectReusult;
    }
    
    public static <T> Object getValueByField(T object, String fieldName){
        Class<?> clazz = object.getClass();
        Object result = null;
        try {
            Field curField = clazz.getDeclaredField(fieldName);
            curField.setAccessible(true);
            result = curField.get(object);
            curField.setAccessible(false);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DataFactoryUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
         return result;
    }
}
