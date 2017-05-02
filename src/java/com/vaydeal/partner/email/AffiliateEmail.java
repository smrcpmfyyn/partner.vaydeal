/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vaydeal.partner.email;


import com.vaydeal.partner.message.URLs;
import java.net.URLEncoder;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * @company techvay
 * @author rifaie
 */
public class AffiliateEmail {
    public static void sendAffiliateUserResetPassword(String to, String passwordToken, String name) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sm.rifaie@gmail.com", "rifuRIFU123#");//change accordingly  
            }
        });

        //compose message  
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sm.rifaie@gmail.com"));//change accordingly  
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Reset Your Password");
        message.setText("Hello " + name + ",\n"
                + "\n"
                + "You need to reset your password before you can sign in to affiliate panal.\n"
                + "--\n"
                + "Please click on the link below to reset your password:\n\n" + URLs.getAFFILIATE_PASSWORD_GENERATION() + URLEncoder.encode(passwordToken,"UTF-8"));

        //send message  
        Transport.send(message);
    }
}
