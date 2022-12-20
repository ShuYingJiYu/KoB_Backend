package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
     //注入接口
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/account/token")
    public Map<Object,Object>getToken(@RequestBody Map<Object,Object>map){
        Object username = map.get("username");
        Object password = map.get("password");
        return loginService.getToken(username,password);
    }
}
