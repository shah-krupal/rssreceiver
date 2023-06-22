package com.example.rssreceiver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "SubscriberData")
public class UserFeed {
    @Id
    String emailId ;
    @Indexed
    List<String>feedTitles ;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<String> getFeedTitles() {
        return feedTitles;
    }

    public void setFeedTitles(List<String> feedTitles) {
        this.feedTitles = feedTitles;
    }
}
