package tn.SIRIUS.EcoCraftLearning;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;



public class Gmail {


        private static final String SMTP_HOST = "smtp.gmail.com";
        private static final String SMTP_PORT = "587";
        private static final String EMAIL_USERNAME = "Hadilzgheb00@gmail.com";
        private static final String EMAIL_PASSWORD = "ohci ugfi adey nbug";
        private static final Session session = createSession();


        private static Session createSession() {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            return Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                }
            });
        }

    public static void EmailTest(String recipientEmail , String notif) {
        try {
            // Génération d'un code aléatoire

            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("notification");

            // Création du contenu HTML avec le code aléatoire
            String emailContentWithSignature = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {" +
                    "    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" +
                    "    background-color: #ecf0f1;" +
                    "    margin: 0;" +
                    "    padding: 0;" +
                    "}" +
                    ".card {" +
                    "    background-color: #ffffff;" +
                    "    color: #34495e;" +
                    "    border-radius: 12px;" +
                    "    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);" +
                    "    overflow: hidden;" +
                    "    width: 80%;" +
                    "    margin: 20px auto;" +
                    "}" +
                    "header {" +
                    "    background-color: #2ecc71;" +
                    "    color: #ffffff;" +
                    "    padding: 20px;" +
                    "    text-align: center;" +
                    "}" +
                    "h2 {" +
                    "    color: #ffffff;" +
                    "}" +
                    "p {" +
                    "    color: #34495e;" +
                    "    line-height: 1.6;" +
                    "    padding: 10px;" +
                    "}" +
                    ".note {" +
                    "    color: #e74c3c;" +
                    "    font-weight: bold;" +
                    "}" +
                    ".content {" +
                    "    padding: 20px;" +
                    "    border-left: 4px solid #2ecc71;" +
                    "    border-right: 4px solid #2ecc71;" +
                    "}" +
                    "footer {" +
                    "    background-color: #2ecc71;" +
                    "    color: #ffffff;" +
                    "    padding: 10px;" +
                    "    text-align: center;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='card'>" +
                    "<header>" +
                    "<h2>Important Notification</h2>" +
                    "</header>" +
                    "<div class='content'>" +
                    "<p> " + notif + "</p>" +

                    "<p class='note'>Note: this mail is auto generated .</p>" +
                    "<p>please dont reply,</p>" +
                    "<p>eco craft learning</p>" +
                    "</div>" +
                    "<footer>" +
                    "<p>&copy; 2024 Your Company Name. All rights reserved.</p>" +
                    "</footer>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            // Ajout du contenu au message
            Multipart multipart = new MimeMultipart();
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(emailContentWithSignature, "text/html");
            multipart.addBodyPart(textPart);
            message.setContent(multipart);

            // Envoi du message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }


    }

