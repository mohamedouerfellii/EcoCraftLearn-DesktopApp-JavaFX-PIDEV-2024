package tn.SIRIUS.entities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EmailSender {

    public static void sendConfirmationEmail(String recipientEmail, String usernames, String city, double total) {
        final String username = "zgachita15@gmail.com";
        final String password = "ybql lfwj powi kkfh";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("zgachita15@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Confirmation de commande");
            new Thread(() -> {
                try {
                    Transport.send(message);
                    System.out.println("Sent message successfully....");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            String htmlFilePath = "C:\\projet Pidev\\EcoCraftLearning\\src\\main\\resources\\Api\\TemplateEmail.html";
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));


            htmlContent = htmlContent.replace("[NamePlaceholder]", usernames);
            htmlContent = htmlContent.replace("[CityPlaceholder]", city);
            htmlContent = htmlContent.replace("[totalPricePlaceholder]", String.valueOf(total));


            message.setContent(htmlContent, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("E-mail de confirmation envoye avec succes à " + recipientEmail);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
        }



    }


}
