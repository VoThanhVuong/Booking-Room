/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

/**
 *
 * @author vuong
 */
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

/**
 * A utility class for sending e-mail messages
 *
 * @author www.codejava.net
 *
 */
public class EmailUtility {

    public static boolean sendEmail(String toEmail, String subject, String message) throws AddressException, MessagingException {
        boolean test = false;
        final String username = "vuonglocan2602@gmail.com";
        final String password = "Vuongvt2k";
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
//            properties.put("mail.smtp.socketFactory.port", "587");
//            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message mess = new MimeMessage(session);

            mess.setFrom(new InternetAddress("vuonglocan2602@gmail.com"));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject(subject);
            mess.setText(message);
            Transport.send(mess);
            test = true;
        } catch (Exception e) {
        }
        return test;
    }
}
