package poly.app.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    private Properties props = new Properties();
    private String from = "fpoly.sampleproject.email@gmail.com";
    private String password = "sampleproject1";
    private Session session;

    private String msgBody;
    private String msgSubject;
    private String to;

    public EmailUtil() {
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.auth", "true");
        session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    public EmailUtil(String to, String msgBody, String msgSubject) {
        this();
        this.to = to;
        this.msgBody = msgBody;
        this.msgSubject = msgSubject;
    }

    public void sendMail() throws Exception {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from, "Hệ thống CINES"));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(msgSubject);
        msg.setContent(msgBody, "text/html; charset=UTF-8");
        Transport.send(msg);

    }
}
