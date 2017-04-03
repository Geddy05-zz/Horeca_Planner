package nl.planner;

import nl.planner.persistence.entity.Person;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;


public class MailService {

    private static final Logger logger = Logger.getLogger(MailService.class.getName());

    public static void sendRegistrationMail(Person person,String BaseUrl){
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("geddy.schellevis@incentro.com", "Horeca Planner"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(person.getMainEmail(), person.getDisplayName()));
            msg.setSubject("Your Horeca Planner account is created");

            msg.setContent("<h1>Your Horeca Planner account is created</h1> <br> " +
                    "<a href=\""+ BaseUrl +"/activate/"+person.getUserId()+"\">click here to activate</a>\n", "text/html; charset=utf-8");

            Transport.send(msg);
        } catch (Exception e) {
            logger.warning(e.toString());
            // ...
        }
    }
}
