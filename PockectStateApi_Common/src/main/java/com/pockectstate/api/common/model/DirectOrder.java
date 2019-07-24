package com.pockectstate.api.common.model;

import lombok.Data;

/**
 *@Author feri
 *@Date Created in 2019/7/24 16:34
 */
@Data
public class DirectOrder {
    private int gid;
    private String sku;
    private int num;
    private int price;
    private int oldprice;
    private int aid;
}
