package com.pockectstate.messages.pockectstatemsg_servermsg.msglistener;

import com.pockectstate.api.common.psenum.MsgLogType;
import com.pockectstate.entity.msg.MsgLog;
import com.pockectstate.messages.pockectstatemsg_servermsg.dao.MsgLogMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.calendar.LocalGregorianCalendar;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 22:33
 */
@Service
@RabbitListener(queues = "EsTaskLog")
public class EsLogListener {
    @Autowired(required = false)
    private MsgLogMapper msgLogMapper;
    @RabbitHandler
    public void process(String msg){
        MsgLog msgLog = new MsgLog();
        msgLog.setContent(msg);
        msgLog.setType(MsgLogType.estasklog.getIndex());
        msgLogMapper.insert(msgLog);
    }
}
