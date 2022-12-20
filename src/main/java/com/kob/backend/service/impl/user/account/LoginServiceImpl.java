package com.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.LoginService;
import com.kob.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    //验证用户登录
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<Object, Object> getToken(Object username, Object password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);//登陆失败会自动处理
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();//取出用户
        User user = loginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getId().toString());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);

        Map<Object,Object>map = new HashMap<>();
        Map<Object,Object>map1 = new HashMap<>();
        map.put("code","200");
        map.put("error_message","success");
        map1.put("token",jwt);
        map1.put("id",userMapper.selectOne(queryWrapper).getId());
        map1.put("username",username);
        map.put("data",map1);
        return map;
    }
}
