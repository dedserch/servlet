package com.serzhputovski.servlet.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSenderUtil {
    private static final String FROM_EMAIL = "dedmemchik@gmail.com";
    private static final String PASSWORD = "xecv hotn vnpi quhh";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";

    public static void sendConfirmationEmail(String recipientEmail, String token) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Confirm your registration");
        String confirmationUrl = "http://localhost:8080/confirm?token=" + token;
        String htmlContent = "<html><body>"
                + "<h2>Please verify your email</h2>"
                + "<p>Click the link below to confirm your registration:</p>"
                + "<a href=\"" + confirmationUrl + "\">Verify Email</a>"
                + "<p>Or copy this link to your browser:</p>"
                + "<p>" + confirmationUrl + "</p>"
                + "</body></html>";
        message.setContent(htmlContent, "text/html");

        Transport.send(message);
    }
}
