package com.pockectstate.server.goods.dao;

import com.pockectstate.entity.goods.GoodsMedia;



public interface GoodsMediaMapper {


    int insert(GoodsMedia record);

    int insertSelective(GoodsMedia record);

}