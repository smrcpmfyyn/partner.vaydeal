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
    
    static final String FROM = "sm.rifaie@gmail.com";

    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
    static final String SMTP_USERNAME = "AKIAJXUMZ3DBYAPXJ74A";  // Replace with your SMTP username.
    static final String SMTP_PASSWORD = "AsnZ1poGtmJkPRiYbaZBBlrZmOXuZ/Lf60Xu78GDNC+F";  // Replace with your SMTP password.

    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    static final String HOST = "email-smtp.eu-west-1.amazonaws.com";

    // The port you will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
    // STARTTLS to encrypt the connection.
    static final int PORT = 25;
    
    public static void sendAffiliateUserResetPassword(String TO, String passwordToken, String name) throws Exception{
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.port", PORT);

        // Set properties indicating that we want to use STARTTLS to encrypt the connection.
        // The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        // Create a Session object to represent a mail session with the specified properties. 
        Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        message.setSubject("Reset Your Password");
        message.setText("Hello " + name + ",\n"
                + "\n"
                + "You need to reset your password before you can sign in to affiliate panal.\n"
                + "--\n"
                + "Please click on the link below to reset your password:\n\n" + URLs.getAFFILIATE_PASSWORD_GENERATION() + URLEncoder.encode(passwordToken,"UTF-8"));

        // Create a transport.        
        Transport transport = session.getTransport();

        // Connect to Amazon SES using the SMTP username and password you specified above.
        transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

        // Send the email.
        transport.sendMessage(message, message.getAllRecipients());
    }
}
