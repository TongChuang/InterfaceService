package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Title: HisTestPurpose
 * Description:His检验目的
 *
 * @Author:yuzh
 * @Date:2016/8/3 14:26
 * @Version:
 */
public class HisTestPurpose implements Serializable {

    private static final long serialVersionUID = 8194592778768089771L;

    @JSONField(name = "Id")
    private String id;          //ID

    @JSONField(name = "Type")
    private String type;        //检验目的类型

    @JSONField(name = "Name")
    private String name;        //检验目的名称

    @JSONField(name = "PinYin")
    private String pinyin;      //检验目的拼音码

    @JSONField(name = "WuBi")
    private String wubi;        //检验目的五笔

    @JSONField(name = "Price")
    private String price;       //单价

    @JSONField(name = "IsOutPatient")
    private String isOutPatient;    //门诊判别

    @JSONField(name = "IsInPatient")
    private String isInPatient;     //住院判别

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getWubi() {
        return wubi;
    }

    public void setWubi(String wubi) {
        this.wubi = wubi;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsOutPatient() {
        return isOutPatient;
    }

    public void setIsOutPatient(String isOutPatient) {
        this.isOutPatient = isOutPatient;
    }

    public String getIsInPatient() {
        return isInPatient;
    }

    public void setIsInPatient(String isInPatient) {
        this.isInPatient = isInPatient;
    }
}
