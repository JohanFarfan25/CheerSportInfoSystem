/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.utils;

import java.util.Date;
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
 * @author johan.farfan
 */
public class Email {
    public static void sendModificacion(String para, String Name, String nombPer, String password) {
        final String user = "";//cambiará en consecuencia al servidor utilizado
        final String pass = "";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Actualizacion de datos en Cheerleading Sports Information Systems");
            java.util.Date fecha = new Date();

            message.setContent(
                    "<center><img src='http://fs5.directupload.net/images/160530/khs5cmdc.jpg' title='Cheerleading Sports Information Systems'></center>"
                    + "<h3> Actualizacion de datos Cheerleading Sports Information Systems. "
                    + Name
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Nombre Persona : "
                    + nombPer
                    + "</h4>"
                    + "<h4> Documento Persona : "
                    + password
                    + " </h4>"
                    + "Ultima Modificacion"
                    + fecha.getHours() + ":" + fecha.getMinutes() + ":" + fecha.getSeconds() + " - "
                    + fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getYear(), "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
    
    //RESTAURAR CLAVE

    public static void sendClaves(String para, String nombreUsuario, String usuario, String password) {
        final String user = "cheersportinfosystem@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes2019";
        
//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Password Update Cheerleading Sports Information System");

            message.setContent(
                    "<center><img src='https://occ-0-1068-1722.1.nflxso.net/dnm/api/v6/E8vDc_W8CLv7-yMQu8KMEC7Rrr8/AAAABWGoYnMXsPqjyEskpgIHZ9f5oEPO69tVn1hA4PucT68KIpzQScUzu6Z2X7rWfLAzes1IdW1F5i353r9z1bWJp52VaclB.jpg?r=fdf' title='Recordatorio Claves Ficha'></center>"
                    + "<h3> Password update. "
                    + nombreUsuario
                    + "</h3>"
                    + "Login Information: "
                    + "<h4> User : "
                    + usuario
                    + "</h4>"
                    + "<h4> New Password : "
                    + password
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendBienvenido(String para, String Nombre, String nombPer, String clave) {
         final String user = "cheersportinfosystem@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes2019";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Bienvenido a Cheerleding Sports Information Systems");

            message.setContent(
                    "<center><img src='https://i.pinimg.com/originals/21/b5/10/21b510a268fc4f7e46607bf482995ae9.png' title='Bienvenidos'></center>"
                    + "<h3> Bienvenido. "
                    + Nombre
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Persona : "
                    + nombPer
                    + "</h4>"
                    + "<h4> Clave Persona : "
                    + clave
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    
   //CCORREO MASIVO
    public static void send(String para, String sujeto, String mensaje) {

        final String user = "cheersportinfosystem@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "Gaes2019";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(sujeto);
            message.setContent( mensaje, "text/html;");
            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    
}
