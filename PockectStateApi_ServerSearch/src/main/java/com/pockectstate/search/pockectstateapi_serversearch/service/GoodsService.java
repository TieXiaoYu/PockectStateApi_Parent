package com.pockectstate.search.pockectstateapi_serversearch.service;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.search.pockectstateapi_serversearch.model.Goods;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 20:39
 */
public interface GoodsService {
    //新增
    R save(Goods goods);
    //删除
    R del(String id);
    //修改
    R update(Goods goods);
    //批量新增
    R saveAll(List<Goods> list);
    R saveBatch(Map<String,String> map);
    //查询 模糊
    R searchLike(String msg,String ip);
}
