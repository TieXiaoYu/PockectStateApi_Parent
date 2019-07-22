package com.pockectstate.search.pockectstateapi_serversearch.model;

import com.pockectstate.api.common.config.ESIndex_Config;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;


/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 20:29
 */

@Data
@Document(indexName = ESIndex_Config.PSGOODS_INDEX,type = ESIndex_Config.PSGOODS_TYPE)
public class Goods {
    private String id;
    private String name;
    private String typeName;
}
