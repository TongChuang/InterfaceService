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
    private String message;     //消息
    @JSONField(name = "Info")
    private Object info;         //内容

    public ReturnMsg() {
    }

    public ReturnMsg(int state, String message, Object info) {
        this.state = state;
        this.message = message;
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
