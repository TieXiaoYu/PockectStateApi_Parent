package com.pockectstate.search.pockectstateapi_serversearch.dao;


import com.pockectstate.search.pockectstateapi_serversearch.model.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 20:33
 */
public interface GoodsDao extends ElasticsearchRepository<Goods,String> {

}
