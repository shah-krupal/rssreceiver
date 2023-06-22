package com.example.rssreceiver.services;

import com.example.rssreceiver.models.Email;
import com.rometools.rome.feed.rss.Channel;

import java.util.List;

public interface MailingService {
    String mailing(Channel channel, List<String> emailList);
}
