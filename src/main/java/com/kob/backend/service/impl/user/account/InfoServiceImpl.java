package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<Object, Object> getinfo() {
        //将信息从上下文中提取出来
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        User user = loginUser.getUser();

        Map<Object,Object>map = new HashMap<>();
        Map<Object,Object>map1 = new HashMap<>();
        map.put("error_message","success");
        map1.put("id",user.getId().toString());
        map1.put("username",user.getUsername());
        map1.put("photo",user.getPhoto());
        map.put("data",map1);
        return map;
    }
}
