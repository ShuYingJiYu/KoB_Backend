package com.kob.backend.service.impl.pk;

import com.kob.backend.consumer.WebSocketServer;
import com.kob.backend.consumer.utils.Game;
import com.kob.backend.service.pk.ReceiveBotMoveService;
import org.springframework.stereotype.Service;

@Service
public class ReceiveBotServiceImpl implements ReceiveBotMoveService {
    @Override
    public String receiveBotMove(Integer userId, Integer direction) {
        System.out.println("receive bot move"+userId+" "+direction+" ");
        //判断user对应的id是否存在
        if(WebSocketServer.users.get(userId)!=null){
            Game game = WebSocketServer.users.get(userId).game;
            if(game!=null){
                if (game.getPlayerA().getId().equals(userId)) {//判断当前传过来的是谁的操作
                    game.setNextStepA(direction);
                } else if (game.getPlayerB().getId().equals(userId) ) {
                    game.setNextStepB(direction);
                }
            }
        }
        return "Receive Bot move success";
    }
}
