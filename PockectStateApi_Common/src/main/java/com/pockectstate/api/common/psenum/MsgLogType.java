package com.pockectstate.api.common.psenum;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 20:08
 * 标记的是消息项目的操作的日志的类型
 */
public enum MsgLogType {
    volidatecode(1),getbackpass(2);
    private int index;

    private MsgLogType(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }}
