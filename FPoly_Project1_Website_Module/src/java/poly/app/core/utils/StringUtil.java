package poly.app.core.utils;

import java.util.Random;

public class StringUtil {
    public static String randomString(){
        String textSample = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        String randomString = "";
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(textSample.length());
            randomString += textSample.charAt(index);   
        }
        return randomString;
    }
    
    public static String randomNumber(int lenght){
        String textSample = "0123456789";
        Random random = new Random();
        String randomString = "";
        for (int i = 0; i < lenght; i++) {
            int index = random.nextInt(textSample.length());
            randomString += textSample.charAt(index);   
        }
        return randomString;
    }
}