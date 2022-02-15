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
public class SendMailGmail {
    String smtpAddress = "smtp.gmail.com"; 
    String smtpPort = "587";
    String enableTLS = "true" ;
    String enableAuth = "true" ;
    final String username = "facesbobtu@gmail.com" ;
    final String password = "0715947545";
    
    //Below is the function for sending the email
public void sendEmail(
			String toAddress,
                        String FirstName,
                        String passkey,
                        String URL,
                        String user_email
){

    String confirmationURL="<a href=\""+URL+"?email="+user_email+"&&passkey="+passkey+"\">Click Here to Confirm Email</a>";
    
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
                + "Thanks,"
                + "<b>FACES Health Informatics Team</b>";
        
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(bodyMessage,"text/html");

        multipart.addBodyPart(textBodyPart);  // add the text part
        msg.setContent(multipart);


        Transport.send(msg);
    } catch (MessagingException e) {
        System.out.println("Error while sending email"+e);
    }
}
}
