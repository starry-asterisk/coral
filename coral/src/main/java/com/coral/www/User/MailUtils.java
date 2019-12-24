package com.coral.www.User;




import java.util.Date;
import java.util.Properties;
 

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;


import java.security.Security;

public class MailUtils {
    
    private Session session;
    private MimeMessage msg;
    private InternetAddress to;
    
    public MailUtils() throws AddressException{
    	
    	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    	 
    	// Get a Properties object
    	Properties p = System.getProperties();
    	p.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    	p.setProperty("mail.smtp.socketFactory.fallback", "false");
        p.setProperty("mail.smtp.socketFactory.port", "465");    
        p.setProperty("mail.smtp.host", "smtp.gmail.com");      
        p.setProperty("mail.smtp.auth","true");                 
        p.setProperty("mail.smtp.port", "465");  
        p.put("mail.smtps.quitwait", "false");
           
        
        //session 생성 및  MimeMessage생성
    	session = Session.getDefaultInstance(p, null);
    	msg = new MimeMessage(session);
        
    }
    
    public void setSubject(String subject) throws MessagingException {
    	// 이메일 제목
        msg.setSubject(subject, "UTF-8");
    }
    
    public void setText(String htmlContent) throws MessagingException {
        msg.setText(htmlContent, "UTF-8");
    }
    
    
    public void setTo(String email) throws MessagingException {
    	// 이메일 수신자
        to = new InternetAddress(email);
        msg.setRecipient(Message.RecipientType.TO, to);
    }
    
    
    public void send() {
        
        try{

            //편지보낸시간
            msg.setSentDate(new Date());
             
            // 이메일 헤더
            msg.setHeader("content-Type", "text/html; charset=UTF-8");
             
            //메일보내기
            SMTPTransport t=(SMTPTransport)session.getTransport("smtps");
            
            t.connect("smtp.gmail.com", "coral15798", "ralco1579*");
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
             
        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }
    }
}
