/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author steph
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendEmailTLS {
    
    public  void sendMail(String emailU) {

        final String username = "lelouchlelouch9@gmail.com";
        final String password = "MnbbDianaNana12";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailU)
            );
            message.setSubject("Confirmation de Don");
            message.setText("Mr/Mdme/Mdlle ,"
                    + "\n\n Ce mail est un accusé de reception suite au don que vous venez d'effectuer sur notre appication HandShake."
                    + ""
                    + "\n\n Nous vous remerçions pour votre confiance."
                    + "\n\n Cordialement.");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
