package com.pockectstate.api.pockectstateapi_serveruser.dao;

import com.pockectstate.entity.user.UserLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLogDao {
    @Insert("insert into t_userlog(uid,type,content,ctime) values(#{uid},#{type},#{content},now())")
    int insert(UserLog log);
    @Select("select id,uid,type,content,ctime from t_userlog order by ctime desc")
    @ResultType(UserLog.class)
    List<UserLog> selectAll();
}
