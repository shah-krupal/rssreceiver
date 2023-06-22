package com.example.rssreceiver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PersonalDetails")
public class UserInfo {
    @Id
    String emailId ;
    String firstName ;
    String companyName ;
    int partnerId ;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "emailId='" + emailId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", partnerId=" + partnerId +
                '}';
    }
}
