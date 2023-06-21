package com.example.rssreceiver.controllers;

import com.example.rssreceiver.models.FeedReceived;
import com.example.rssreceiver.services.WebHookService;
import com.rometools.rome.feed.rss.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebHookController {
    @Autowired
    private WebHookService webHookService ;

    @PostMapping(value = "/webhook", consumes = MediaType.APPLICATION_RSS_XML_VALUE)
    private String hook(@RequestBody Channel channel)
    {
        return webHookService.hook(channel) ;
    }

    @PostMapping("/feedhook")
    private String feedhook(@RequestBody FeedReceived feedReceived){
        return webHookService.feedhook(feedReceived) ;

    }
}
