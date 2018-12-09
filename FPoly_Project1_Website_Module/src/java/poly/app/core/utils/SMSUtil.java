package poly.app.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMSUtil {

//    private static final String API_KEY = "OwLaf+jOXs4-DQ6GYLlDvYypJ3TPg99zcDL6V3lW0Y";
//    private static final String SENDER_NAME = "CINES";
//
//    public static boolean sendSMS(String sendMessage, String sendNumber) {
//        sendNumber = SMSUtil.convertToInternationalPhoneNumber(sendNumber);
//        try {
//            // Construct data
//            String apiKey = "apikey=" + API_KEY;
//            String message = "&message=" + sendMessage;
//            String sender = "&sender=" + SENDER_NAME;
//            String numbers = "&numbers=" + sendNumber;
//
//            // Send data
//            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
//            String data = apiKey + numbers + message + sender;
//            conn.setDoOutput(true);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//            conn.getOutputStream().write(data.getBytes("UTF-8"));
//            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            final StringBuffer stringBuffer = new StringBuffer();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                stringBuffer.append(line);
//            }
//            rd.close();
//            System.out.println(stringBuffer.toString());
//            return isSuccess(stringBuffer.toString());
//        } catch (Exception e) {
//            System.out.println("Error SMS " + e);
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private static boolean isSuccess(String responseJSON) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            map = mapper.readValue(responseJSON, new TypeReference<Map<String, Object>>() {
//            });
//        } catch (IOException ex) {
//            Logger.getLogger(SMSUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return map.get("status").equals("success");
//    }
//
//    public static String convertToInternationalPhoneNumber(String phoneNumber) {
//        String phoneFormated = "84";
//        phoneFormated += phoneNumber.substring(1);
//        return phoneFormated;
//    }
}
