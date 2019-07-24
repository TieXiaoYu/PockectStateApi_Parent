package com.pockectstate.messages.pockectstatemsg_servermsg.msglistener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 *@Author feri
 *@Date Created in 2019/7/24 16:52
 */
@Service
@RabbitListener(queues = "")
public class OrderLogLisener {
    @RabbitHandler
    public void process(String msg){

    }
}
