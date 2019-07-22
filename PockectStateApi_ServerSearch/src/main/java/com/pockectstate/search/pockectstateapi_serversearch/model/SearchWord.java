package com.pockectstate.search.pockectstateapi_serversearch.model;

import com.pockectstate.api.common.config.ESIndex_Config;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 20:29
 */
@Data
@Document(indexName = ESIndex_Config.PSSEARCH_INDEX,type = ESIndex_Config.PSSEARCH_TYPE)
public class SearchWord {
    private String id;
    private String world;
    private String ipaddr;
}
