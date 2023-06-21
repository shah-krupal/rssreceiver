package com.example.rssreceiver.services;

import com.example.rssreceiver.models.Email;
import com.example.rssreceiver.models.FeedReceived;
import com.rometools.rome.feed.rss.Channel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WebHookService {
    String hook(Channel channel);
    String mailing(Email email, List<String> mailIds) ;

    String feedhook(FeedReceived feedReceived);

    String mailing2(Email email, List<String> mailIds) ;
}
