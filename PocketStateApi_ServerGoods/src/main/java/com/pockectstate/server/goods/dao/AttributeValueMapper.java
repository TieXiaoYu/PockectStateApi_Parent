package com.pockectstate.server.goods.dao;

import com.pockectstate.entity.goods.AttributeValue;


public interface AttributeValueMapper {


    int insert(AttributeValue record);

    int insertSelective(AttributeValue record);

}