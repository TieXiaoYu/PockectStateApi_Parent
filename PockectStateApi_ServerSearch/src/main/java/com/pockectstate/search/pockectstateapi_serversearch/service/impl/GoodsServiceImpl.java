package com.pockectstate.search.pockectstateapi_serversearch.service.impl;

import com.pockectstate.api.common.config.ESIndex_Config;

import com.pockectstate.api.common.util.IdGenerator;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.search.pockectstateapi_serversearch.dao.GoodsDao;
import com.pockectstate.search.pockectstateapi_serversearch.dao.SearchWorldDao;
import com.pockectstate.search.pockectstateapi_serversearch.model.Goods;
import com.pockectstate.search.pockectstateapi_serversearch.model.SearchWord;
import com.pockectstate.search.pockectstateapi_serversearch.service.GoodsService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 20:52
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SearchWorldDao searchWorldDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private IdGenerator idGenerator;
    @Override
    public R save(Goods goods) {
        return R.setR(goodsDao.save(goods)!= null,"OK",null);
    }

    @Override
    public R del(String id) {

        goodsDao.deleteById(id);
        return R.setOK("OK",null);
    }

    @Override
    public R update(Goods goods) {
        return R.setR(goodsDao.save(goods) == null,"OK",null);
    }

    @Override
    public R saveAll(List<Goods> list) {
        goodsDao.saveAll(list);
        return R.setOK("OK",null);
    }

    @Override
    public R saveBatch(Map<String,String> map) {
        List<IndexQuery> queries = new ArrayList<>();
        for(String k:map.keySet()){
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setIndexName(ESIndex_Config.PSGOODS_INDEX);
            indexQuery.setId(k);
            indexQuery.setType(ESIndex_Config.PSGOODS_TYPE);
            indexQuery.setVersion(1L);
            indexQuery.setSource(map.get(k));
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return R.setOK("OK",null);
    }

    @Override
    public R searchLike(String msg,String ip) {
        if(msg != null &&msg.length() > 0){
            msg="*"+msg+"*";
            WildcardQueryBuilder w1 = QueryBuilders.wildcardQuery("name",msg);
            WildcardQueryBuilder w2 = QueryBuilders.wildcardQuery("typeName",msg);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.should(w1);
            boolQueryBuilder.should(w2);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);
            Iterable<Goods> iterable = goodsDao.search(searchSourceBuilder.query());
           //记录搜索关键词
            SearchWord searchWord = new SearchWord();
            searchWord.setId(idGenerator.nextId()+"");
            searchWord.setIpaddr(ip);
            searchWorldDao.save(searchWord);
            return R.setOK("OK",null);
        }else {
            return null;
        }

    }
}
