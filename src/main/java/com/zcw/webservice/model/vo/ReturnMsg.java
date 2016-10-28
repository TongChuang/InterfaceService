package com.zcw.webservice.model.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by zcw on 2016/8/10.
 */
public  class ReturnMsg implements Serializable{
    private static final long serialVersionUID = 3538219950834176614L;

    @JSONField(name = "State")
    private int state;          //状态值
    @JSONField(name = "Message")
    private Object message;     //消息

    public ReturnMsg() {
    }

    public ReturnMsg(int state, Object message) {
        this.state = state;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

}
