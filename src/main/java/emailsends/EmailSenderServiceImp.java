package emailsends;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.Session;


public class EmailSenderServiceImp implements IEmailSenderService{
    private EmailConfiguration configuration = new EmailConfiguration();
    private EmailSender emailSender = new EmailSender();

    @Override
    public Session configureEmailSMTP(String fromEmail, String password,
                                      String host, int sslOrTlsEnable) {
        return configuration.configureEmailSMTP(fromEmail, password, host, sslOrTlsEnable);
    }

    @Override
    public void sendEmail(Session session, String toEmail, String fromEmail,
                          String senderName, String body, String subject, String messageId, String[] attachments) throws MessagingException, UnsupportedEncodingException {
        emailSender.sendEmail(session, toEmail, fromEmail, senderName, body, subject,messageId,attachments);
    }

}
