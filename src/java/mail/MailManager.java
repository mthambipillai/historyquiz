/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 *
 * @author Melkior
 */
public class MailManager {
    private String smtpServ;
    
    public static final String serverAddress = "mthambip@netplus.ch";
    
    public static final String welcomeMailSubject = "Welcome! Bienvenue!";
    
    public static final String welcomeMailMessage = 
            "Welcome to History Quiz! You are now a member!\n"
            + "Bienvenue sur History Quiz! Vous êtes maintenant membre!";
    
    public MailManager(String server){
        smtpServ = server;
    }
    
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username =  serverAddress;           // specify your email id here (sender's email id)
            String password = "Thambip187";                                      // specify your password here
            return new PasswordAuthentication(username, password);
        }
    }
    
    public int sendWelcomeMail(String to){
        return sendMail(serverAddress,to,welcomeMailMessage,welcomeMailSubject);
    }
    
    public int sendMail(String from, String to, String message, String subject){
        try
        {
            Properties props = System.getProperties();
              // -- Attaching to default Session, or we could start a new one --
              props.put("mail.transport.protocol", "smtp" );
              props.put("mail.smtp.starttls.enable","true" );
              props.put("mail.smtp.host",smtpServ);
              props.put("mail.smtp.auth", "true" );
              props.put("mail.smtp.ssl.trust", "smtp.netplus.ch");
              Authenticator auth = new SMTPAuthenticator();
              Session session = Session.getInstance(props, auth);
              // -- Create a new message --
              Message msg = new MimeMessage(session);
              // -- Set the FROM and TO fields --
              msg.setFrom(new InternetAddress(from));
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              msg.setSubject(subject);
              msg.setText(message);
              // -- Set some other header information --
              msg.setHeader("MyMail", "Mr. XYZ" );
              msg.setSentDate(new Date());
              // -- Send the message --
              Transport.send(msg);
              return 0;
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
          return -1;
        }
  }
}
