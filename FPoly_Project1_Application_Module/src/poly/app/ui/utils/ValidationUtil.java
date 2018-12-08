package poly.app.ui.utils;

import java.text.SimpleDateFormat;

public class ValidationUtil {
    //k đc bỏ trống
    public static boolean isEmpty(String input){
        return input.equals("");
    }
    //k vượt quá giới hạn
    public static boolean isLenghtEnought(String input, int lenght){
        return input.length() >= lenght;
    }
    //phải = số đã ra điều kiện
    public static boolean isLenghtEqual(String input, int lenght){
        return input.length() == lenght;
    }
    //định dạng email
    public static boolean isValidEmail(String input){
        return input.matches("^([\\w\\-\\.]+){2,64}\\@(\\w){2,255}(\\.[a-z]{2,10}){1,2}$");
    }
    
    public static boolean isNumber(String input){
        return input.matches("^0.*");
    }
    //xác thực phải là số
    public static boolean isValidDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.format(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static <T> boolean isValidNumber(T type, String number){
        if (type.getClass().equals(Integer.class)) {
            try {
                Integer.parseInt(number);
            } catch (Exception e) {
                return false;
            }
        }else if(type.getClass().equals(Double.class)){
            try {
                Double.parseDouble(number);
            } catch (Exception e) {
                return false;
            }
        }else if(type.getClass().equals(Float.class)){
            try {
                Float.parseFloat(number);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}