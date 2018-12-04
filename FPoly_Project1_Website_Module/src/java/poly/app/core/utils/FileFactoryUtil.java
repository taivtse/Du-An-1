package poly.app.core.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileFactoryUtil {
    public static <T> T readObject(T data, String path){        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
        
            data = (T) ois.readObject();
        }catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(ois != null)
                    ois.close();

                if(fis != null)
                    fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }
    
    public static <T> boolean writeObject(T data, String path){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean isSuccess = true;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(data);
        } catch (IOException ex) {
            isSuccess = false;
            ex.printStackTrace();
        }finally{
            try {
                if(oos != null)
                    oos.close();

                if(fos != null)
                    fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return isSuccess;
    }
}
