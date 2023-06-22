package com.example.rssreceiver.services;

import com.example.rssreceiver.models.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {
    String signup(UserInfo userInfo) ;
}
