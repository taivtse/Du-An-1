package poly.app.core.api.helper;

import java.util.HashMap;
import java.util.Map;
import poly.app.core.utils.StringUtil;

public class TicketBookingHelper {
    private static Map<String, String> tokenMap = new HashMap<>();
    
    public static String generateToken(String khachHangID){
        String token = StringUtil.generateMD5(System.nanoTime() + "");
        tokenMap.put(khachHangID, token);
        return token;
    }
    
    public static boolean isTokenAvailable(String khachHangID, String token){
        if (tokenMap.containsKey(khachHangID)) {
            return tokenMap.get(khachHangID).equals(token);
        }
        return false;
    }
    
    public static String getTokenByKhachHangID(String khachHangID){
        return tokenMap.get(khachHangID);
    }
    
    public static String saveToken(String khachHangID){
        String token = generateToken(khachHangID);
        tokenMap.put(khachHangID, token);
        return  token;
    }
}