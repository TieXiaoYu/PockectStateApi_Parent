package com.pockectstate.server.goods.service;

import com.pockectstate.api.common.vo.R;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-16 22:37
 */
public interface GoodsService {

    R queryDetail(int gid);
    R queryHot();//热门商品
    R queryLike(String token);//根据用户的浏览排名
    R querypage();
}
