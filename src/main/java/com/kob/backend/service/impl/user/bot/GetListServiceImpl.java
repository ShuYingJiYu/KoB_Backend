package com.kob.backend.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.BotMapper;
import com.kob.backend.pojo.Bot;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetListServiceImpl implements GetListService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<Object,Object> getList() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        Map<Object,Object> map1 = new HashMap<>();
        Map<Object,Object> map = new HashMap<>();
        map.put("code",200);
        map1.put("botList",botMapper.selectList(queryWrapper));
        map1.put("botCount",botMapper.selectCount(queryWrapper));
        map.put("data",map1);
        return map;
    }
}
