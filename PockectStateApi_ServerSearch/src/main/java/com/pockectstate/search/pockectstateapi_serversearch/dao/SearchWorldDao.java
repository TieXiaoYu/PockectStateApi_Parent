package com.pockectstate.search.pockectstateapi_serversearch.dao;

import com.pockectstate.search.pockectstateapi_serversearch.model.SearchWord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 20:38
 */
public interface SearchWorldDao extends ElasticsearchRepository<SearchWord,String> {
}
