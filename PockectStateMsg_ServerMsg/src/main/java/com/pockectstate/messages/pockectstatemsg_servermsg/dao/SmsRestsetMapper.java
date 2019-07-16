package com.pockectstate.messages.pockectstatemsg_servermsg.dao;

import com.pockectstate.entity.msg.SmsRestset;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsRestsetMapper {

    int insert(SmsRestset record);

}