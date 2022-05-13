package emailsends;


import businessLogic.BlFacade;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;


public class SendEmail {
    BlFacade bl;
    private  int code;
    public int sendEmail(String email,String fname,String lname) {

        Random random = new Random();
        this.code=1000+random.nextInt(8999);

        String fromEmail = "hejbanewin@gmail.com";
        String password = "lmwijepcpevuxupw" ;
        String host = "smtp.gmail.com";

        String toEmail = email;
        String body = "Hello "+fname +" "+lname+"\nYour confirmation code is : "+code+".\n Thank you for your registration.\nBet&Ruin.";
        String subject = "Confirmation code";
        String messageId = "<CAN8Q0nr3-iq9-=-=7u1NyhkNAa+1wc-9ZOgwE8DMpXViWcGsKg@mail.gmail.com>";
        EmailConfiguration configuration = new EmailConfiguration();
        EmailSender emailSender = new EmailSender();
        Session session = configuration.configureEmailSMTP(fromEmail, password, host, 1);
        try {
            emailSender.sendEmail(session, toEmail, fromEmail, "Bet&Ruin", body, subject, messageId,null);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }




    public void receiveEmailContactUs(String email,String fname,String lname,String Subject,String Description) {


        String fromEmail = email;
        String password = "lmwijepcpevuxupw" ;
        String host = "smtp.gmail.com";

        String toEmail ="hejbanewin@gmail.com" ;
        String body = "Hi Bet&RuinYour,\n I am "+fname+" "+lname +" i want to contact you about "+Subject+" and the is the description:\n"+Description;
        String subject = "Contact Us";
        String messageId = "<CAN8Q0nr3-iq9-=-=7u1NyhkNAa+1wc-9ZOgwE8DMpXViWcGsKg@mail.gmail.com>";
        EmailConfiguration configuration = new EmailConfiguration();
        EmailSender emailSender = new EmailSender();
        Session session = configuration.configureEmailSMTP(fromEmail, password, host, 1);
        try {
            emailSender.sendEmail(session, toEmail, fromEmail, "Bet&Ruin", body, subject, messageId,null);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void sendEmailContactUs(String email,String fname,String lname,String Subject,String Description) {


        String fromEmail ="hejbanewin@gmail.com" ;
        String password = "lmwijepcpevuxupw" ;
        String host = "smtp.gmail.com";

        String toEmail = email;
        String body = "Hi,\n"+fname+" "+lname +"we are receive your email about "+Subject+" We will answer you as soon as possible\nBet&Ruin";
        String subject = "Contact Us";
        String messageId = "<CAN8Q0nr3-iq9-=-=7u1NyhkNAa+1wc-9ZOgwE8DMpXViWcGsKg@mail.gmail.com>";
        EmailConfiguration configuration = new EmailConfiguration();
        EmailSender emailSender = new EmailSender();
        Session session = configuration.configureEmailSMTP(fromEmail, password, host, 1);
        try {
            emailSender.sendEmail(session, toEmail, fromEmail, "Bet&Ruin", body, subject, messageId,null);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
