package com.example.rssreceiver.models;

public class SubscribeRequest {
    String emailId ;
    Integer feedId ;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }
}
