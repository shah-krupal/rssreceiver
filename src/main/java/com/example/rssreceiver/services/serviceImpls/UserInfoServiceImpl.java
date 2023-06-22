package com.example.rssreceiver.services.serviceImpls;

import com.example.rssreceiver.models.UserInfo;
import com.example.rssreceiver.repositories.UserInfoRepo;
import com.example.rssreceiver.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepo userInforepo ;
    @Override
    public String signup(UserInfo userInfo) {
        try{
            System.out.println(userInfo);
            userInforepo.save(userInfo) ;
            return "Signup Succesful" ;
        }catch(Exception e)
        {
            e.printStackTrace();
            return e.getMessage() ;
        }
    }
}
