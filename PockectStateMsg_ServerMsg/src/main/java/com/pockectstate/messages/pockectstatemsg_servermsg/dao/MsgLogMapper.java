package com.pockectstate.messages.pockectstatemsg_servermsg.dao;

import com.pockectstate.entity.msg.MsgLog;

import java.util.List;
public interface MsgLogMapper {

    long count();

    int insert(MsgLog record);
}