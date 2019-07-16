package com.pockectstate.server.goods.dao;

import com.pockectstate.entity.goods.Goods;

public interface GoodsMapper {

    int insert(Goods record);

    int insertSelective(Goods record);

}