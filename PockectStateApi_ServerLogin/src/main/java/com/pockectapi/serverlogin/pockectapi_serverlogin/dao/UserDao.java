package com.pockectapi.serverlogin.pockectapi_serverlogin.dao;

import com.pockectstate.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-11 19:25
 */
public interface UserDao {
    @Select("select id,phone,password from t_user where flag=1 and phone = #{phone}")
    @ResultType(User.class)
    User selectByPhone(String phone);
    @Update("update t_user set password = #{password} where phone = #{phone}")
    int updatePass(@Param("phone") String phone,@Param("password") String pass);
}
