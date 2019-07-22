package com.pockectstate.search.pockectstateapi_serversearch.controller;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.search.pockectstateapi_serversearch.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 21:26
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("search/goodslike.do")
    public R search(@RequestParam String m, HttpServletRequest request){
        return goodsService.searchLike(m,request.getRemoteAddr());
    }
}
