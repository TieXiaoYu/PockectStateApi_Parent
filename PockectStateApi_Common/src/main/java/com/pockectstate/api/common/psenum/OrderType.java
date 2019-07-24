package com.pockectstate.api.common.psenum;

/**
 *@Author feri
 *@Date Created in 2019/7/24 16:27
 */
public enum OrderType {
    nopay(1),nosend(2),noreceive(3),notalk(4);
    private int value;
    private OrderType(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
