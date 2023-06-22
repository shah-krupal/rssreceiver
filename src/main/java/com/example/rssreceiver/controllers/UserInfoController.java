package com.example.rssreceiver.controllers;

import com.example.rssreceiver.models.UserInfo;
import com.example.rssreceiver.repositories.UserInfoRepo;
import com.example.rssreceiver.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService ;
    @PostMapping("/signup")
    public String signup(@RequestBody UserInfo userInfo)
    {
        return userInfoService.signup(userInfo) ;
    }
}
