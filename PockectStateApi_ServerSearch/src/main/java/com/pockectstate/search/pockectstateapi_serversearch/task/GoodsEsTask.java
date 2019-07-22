package com.pockectstate.search.pockectstateapi_serversearch.task;

import com.pockectstate.api.common.config.RedisKey_config;
import com.pockectstate.api.common.util.JedisUtil;
import com.pockectstate.search.pockectstateapi_serversearch.config.RabbitMQConfig;
import com.pockectstate.search.pockectstateapi_serversearch.service.GoodsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 21:30
 */
@Service
public class GoodsEsTask {
    @Autowired
    private GoodsService goodsService;
    private JedisUtil jedisUtil = JedisUtil.getInstance();
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Scheduled(cron = "0 0 0/4 * * ? ")
    public void tbes(){
        //数据变化
        //新增
        //修改
        //删除
        //从数据库 --读取 -- 商品流水 -- 发送消息 --RabbitMQ -- 消息消费者 --获取消息
        //从Redis --读取3个Key   Hash -- 防止数据不一致或者雪崩 双key机制
        //1个key 有效期 4小时10分钟  另一个key永久有效
        //依次验证key
        //发布消息 定时触发  开始执行
        rabbitTemplate.convertAndSend(RabbitMQConfig.queuelog,"ES定时任务开始执行");
        //搬运工 Redis的数据搬到ES服务器
       opEs(RedisKey_config.ESHASHADD);
       opEs(RedisKey_config.ESHASHDEL);
       opEs(RedisKey_config.ESHASHUPDATE);
        //发布消息 定时触发  执行结束
        rabbitTemplate.convertAndSend(RabbitMQConfig.queuelog,"ES定时任务执行结束");

    }
    private void opEs(String key){
        if(jedisUtil.exists(key)){
            Map<String,String> map = jedisUtil.hgetall(RedisKey_config.ESHASHADD);
            goodsService.saveBatch(map);
            jedisUtil.del(key);
            jedisUtil.del(":slave");
        }else if(jedisUtil.exists(":slave")){
            Map<String,String> map = jedisUtil.hgetall(RedisKey_config.ESHASHADD);
            goodsService.saveBatch(map);
            jedisUtil.del(":slave");
        }
    }
}
