package com.example.rssreceiver.services.serviceImpls;

import com.example.rssreceiver.models.Email;
import com.example.rssreceiver.models.FeedReceived;
import com.example.rssreceiver.models.FeedSubscriber;
import com.example.rssreceiver.models.UserFeed;
import com.example.rssreceiver.repositories.FeedSubscriberRepo;
import com.example.rssreceiver.services.MailingService;
import com.example.rssreceiver.services.WebHookService;
import com.rometools.rome.feed.rss.Channel;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WebHookServiceImpl implements WebHookService {
    @Autowired
    private MailingService mailingService;
    @Autowired
    private FeedSubscriberRepo feedSubscriberRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String MAIL_SERVER = "smtp";
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 587;
    private static final String USER_NAME = "shahdummy91"; // GMail user name (just the part before "@gmail.com")
    private static final String PASSWORD = "xqrjohmbnhxhylnv"; // GMail password

    @Override
    public String hook(Channel channel) {
        if (channel == null)
            return "Null Channel";
        try {
            System.out.println("channel " + channel.getTitle());
            Query query = new Query();
            query.addCriteria(Criteria.where("feedTitles").is(channel.getTitle()));

            List<UserFeed> users = mongoTemplate.find(query, UserFeed.class);
            List<String> emailList = new ArrayList<>();
            for (UserFeed feed : users) {
                emailList.add(feed.getEmailId());
            }

            if (emailList != null) {
                System.out.println(emailList);
                return mailing2(channel, emailList);
            } else {
                return "DONE";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Failure to connect to DB";
        }

    }

    @Override
    public String mailing(Email email, List<String> mailIds) {
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.user", USER_NAME);
        properties.put("mail.smtp.password", PASSWORD);
        properties.put("mail.smtp.port", SMTP_HOST_PORT);
        properties.put("mail.smtp.auth", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        try {
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(USER_NAME));

            // NO TO or CC. ONLY BCC
            // InternetAddress[] toAddress = new InternetAddress[mailIds.size()];
            //
            // // To get the array of toaddresses
            // for( int i = 0; i < mailIds.size(); i++ ) {
            // toAddress[i] = new InternetAddress(mailIds.get(i));
            // }
            //
            // // Set To: header field of the header.
            // for( int i = 0; i < toAddress.length; i++) {
            // message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            // }
            //
            // InternetAddress[] ccAddress = new InternetAddress[cc.length];
            //
            // // To get the array of ccaddresses
            // for( int i = 0; i < cc.length; i++ ) {
            // ccAddress[i] = new InternetAddress(cc[i]);
            // }
            //
            // // Set cc: header field of the header.
            // for( int i = 0; i < ccAddress.length; i++) {
            // message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
            // }

            InternetAddress[] bccAddress = new InternetAddress[mailIds.size()];

            // To get the array of bccaddresses
            for (int i = 0; i < mailIds.size(); i++) {
                bccAddress[i] = new InternetAddress(mailIds.get(i));
            }

            // Set bcc: header field of the header.
            for (int i = 0; i < bccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
            }

            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Now set the date to actual message
            message.setSentDate(new Date());

            // Now set the actual message
            message.setContent(email.getBody(), "text/html");

            System.out.println("Get Session and Send Mail");
            // Send message
            Transport transport = session.getTransport(MAIL_SERVER);
            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent Message Successfully....");
            return "Sent Message Successfully.";
        } catch (AddressException ae) {
            System.out.println("address it is");
            ae.printStackTrace();
            return "ADDRESS EXCEPTION";
        } catch (MessagingException me) {
            System.out.println("message it is");
            me.printStackTrace();
            return "MESSAGE EXCEPTION";
        }

    }

    @Override
    public String feedhook(FeedReceived feedReceived) {
        try {
            FeedSubscriber feedSubscriber = new FeedSubscriber();
            feedSubscriber.setFeedId(feedReceived.getFeedId());
            feedSubscriber.setFeedTitle(feedReceived.getTitle());
            feedSubscriberRepo.save(feedSubscriber);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    @Override
    public String mailing2(Email email, List<String> mailIds) {
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.user", USER_NAME);
        properties.put("mail.smtp.password", PASSWORD);
        properties.put("mail.smtp.port", SMTP_HOST_PORT);
        properties.put("mail.smtp.auth", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        try {
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(USER_NAME));

            // Set BCC: header field of the header.
            for (String mailId : mailIds) {
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mailId));
            }

            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Now set the date to actual message
            message.setSentDate(new Date());

            // Now set the actual message
            message.setContent(email.getBody(), "text/html");

            System.out.println("Get Session and Send Mail");
            // Send message
            Transport transport = session.getTransport(MAIL_SERVER);
            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent Message Successfully....");
            return "Sent Message Successfully.";
        } catch (AddressException ae) {
            System.out.println("address it is");
            ae.printStackTrace();
            return "ADDRESS EXCEPTION";
        } catch (MessagingException me) {
            System.out.println("message it is");
            me.printStackTrace();
            return "MESSAGE EXCEPTION";
        }
    }

}
