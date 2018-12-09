package poly.app.core.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSUtil {

    public static final String ACCOUNT_SID
            = "AC801901f600770984f749e25ce29942f8";
    public static final String AUTH_TOKEN
            = "c5c7a33285655cc98d09f54ffadd00dd";

    public static final String SERVER_PHONE_NUMBER
            = "+17029034955";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public static boolean sendSMS(String sendMessage, String sendNumber) {
        sendNumber = SMSUtil.convertToInternationalPhoneNumber(sendNumber);
        try {
            Message.creator(
                    new PhoneNumber(sendNumber), // to
                    new PhoneNumber(SMSUtil.SERVER_PHONE_NUMBER), // from
                    sendMessage)
                    .create();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String convertToInternationalPhoneNumber(String phoneNumber) {
        String phoneFormated = "+84";
        phoneFormated += phoneNumber.substring(1);
        return phoneFormated;
    }
}
