package com.pockectstate.messages.pockectstatemsg_servermsg.dao;

import com.pockectstate.entity.msg.SmsRestset;
import com.pockectstate.entity.msg.SmsSend;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsSendMapper {

    int insert(SmsSend record);

    List<SmsSend> selectAll();

    SmsSend selectByMsg(@Param("phone") String phone,@Param("msg") String msg);


}