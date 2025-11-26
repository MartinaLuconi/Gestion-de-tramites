/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion;

import IniciarSesion.dto.DTOMensaje;
import entidades.Usuario;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author marti
 */
public class AdaptadorMail implements AdaptadorMsj{
    

    public String enviarMensaje(DTOMensaje d){
             
  String host="hosting.debstudio.com.ar";  
  final String user="losimaginadores@debstudio.com.ar";//change accordingly  
  final String password="12345678!";//change accordingly  
    
  String to=d.getUsuario().getUsuario();//change accordingly  
  
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.ssl.trust", "hosting.debstudio.com.ar");
    props.put("mail.smtp.starttls.enable", "true"); // Conexió segura al servidor
    props.put("mail.smtp.port", "587"); // Puerto SMTP seguro de Google
      props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    props.put("mail.smtp.starttls.enable", "true");
//   props.put("mail.smtp.starttls.enable", "true");
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject(d.getAsunto());  
     message.setText(d.getMensaje());  
       
    //send the message  
     Transport.send(message);  
  
     System.out.println("message sent successfully...");  
     
     } catch (MessagingException e) {e.printStackTrace();return e.getMessage();}  
       
             return "";
    }
    
    }

