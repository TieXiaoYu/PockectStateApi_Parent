package com.pockectstate.api.common.psenum;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-11 19:45
 */
public enum DeviceType {
    android(1),iosphone(2),pchtml(3),wechat(4);
    private int value;
    private DeviceType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
