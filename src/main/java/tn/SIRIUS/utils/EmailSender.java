package tn.SIRIUS.utils;

import tn.SIRIUS.entities.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EmailSender {
    public static void send(User user, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protokls", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mait.smtp.port", "2525");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("6676c6a9551f66", "1b6fd7f1b1b3cd");
            }
        });

        String htmlFilePath = "/gui/mailFeedbackTemplate.html";
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
            htmlContent = htmlContent.replace("[titlePlaceholder]", content);
            htmlContent = htmlContent.replace("[msgPlaceholder]", content);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Contact@zerowaste.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("test");
            message.setContent(htmlContent, "text/html");
            Transport.send(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
