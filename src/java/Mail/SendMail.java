/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Geofrey Nyabuto
 */
public class SendMail {
    String smtpAddress = "mail.kemri-ucsf.org"; 
    String smtpPort = "587";
    String enableTLS = "true" ;
    String enableAuth = "true" ;
    final String username = "validate@kemri-ucsf.org" ;
    final String password = "@Mail20#g%$#";
    
    //Below is the function for sending the email
public boolean sendEmail(
			String toAddress,
                        String FirstName,
                        String passkey,
                        String URL,
                        String Direct_URL
){

    String confirmationURL="<a href=\""+Direct_URL+"?email="+toAddress+"&&passkey="+passkey+"\">Click Here to Confirm Email</a>";
    
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", enableTLS);
        props.put("mail.smtp.auth", enableAuth);
        props.put("mail.smtp.host", smtpAddress);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

            Message msg = new MimeMessage(session);
    try {
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
        msg.setSubject("FACES Ushauri Reports Key");

        Multipart multipart = new MimeMultipart();

        String bodyMessage="Dear <b>"+FirstName+"</b>,<br/>"
                + "Your confirmation Key for FACES Ushauri reporting module is "+passkey+"<br/>"
                + " Click on "+confirmationURL+".<br/> "
                + "If you have problems using above URL, Kindly <b><a href=\""+URL+"\"> Click Here to Enter PassKey</a></b> and confirm you email. <br/>"
                + "<br>"
                + "Thanks,<br>"
                + "<b style=\"color:red; font-weight:900;\">FACES Health Informatics Team</b>.<br>"
                + "In case you did not register to our system, Kindly ignore this email address";
        
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(bodyMessage,"text/html");

        multipart.addBodyPart(textBodyPart);  // add the text part
        msg.setContent(multipart);


        Transport.send(msg);
        return true;
    } catch (MessagingException e) {
        System.out.println("Error while sending email"+e);
        return false;
    }
}
    //Below is the function for sending the email
public boolean sendPassCode(
			String toAddress,
                        String FirstName,
                        String passkey
){

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", enableTLS);
        props.put("mail.smtp.auth", enableAuth);
        props.put("mail.smtp.host", smtpAddress);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

            Message msg = new MimeMessage(session);
    try {
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
        msg.setSubject("FACES Ushauri Reports Pass Code");

        Multipart multipart = new MimeMultipart();

        String bodyMessage="Dear <b>"+FirstName+"</b>,<br/>"
                + "You initiated a password reset process. We have generated Pass Code for you to use while setting up your new password. <br>"
                + "Your Pass Code is <b>"+passkey+"</b><br/>"
                + "<br>"
                + "Thanks,<br>"
                + "<b style=\"color:red; font-weight:900;\">FACES Health Informatics Team</b>"
                + "<br>"
                + "<br>"
                + "<i>If you did not initiate this process, kindly ignore this email</i>";
        
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(bodyMessage,"text/html");

        multipart.addBodyPart(textBodyPart);  // add the text part
        msg.setContent(multipart);


        Transport.send(msg);
        return true;
    } catch (MessagingException e) {
        System.out.println("Error while sending email"+e);
        return false;
    }
}

public boolean sendAdminEmail(
			String toAddress,
        String fullName,
        String user_email
){
    
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", enableTLS);
        props.put("mail.smtp.auth", enableAuth);
        props.put("mail.smtp.host", smtpAddress);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

            Message msg = new MimeMessage(session);
    try {
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddress));
        msg.setSubject("New User Registration Alert");

        Multipart multipart = new MimeMultipart();

        String bodyMessage="Hi,<br/>"
                + "This is to notify you that a new user [<b>"+fullName+"</b>] with email address <b> "+user_email+"</b> has registered into the system. Kindly log into the system, verify and approve new users.<br>"
                + "<b style=\"color:red; font-weight:700;\">Kindly note that users who are not approved/active will not be able to login to the system.</b> <br/>"
                + "<br>"
                + "<b style=\"background-color:#f2e2dc; color:#1f3d6e; font-weight:900; padding:2px;\">Thank You,<br>"
                + "FACES Health Informatics Team</b>.<br>";
        
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(bodyMessage,"text/html");

        multipart.addBodyPart(textBodyPart);  // add the text part
        msg.setContent(multipart);


        Transport.send(msg);
        
        return true;
    } catch (MessagingException e) {
    return false;
    }
}
}
