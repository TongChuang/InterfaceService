/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Title: .IntelliJ IDEA
 * Description:
 *
 * @version \$Id DrugResult.java, v 0.1 2016-08-09 16:50 zhou Exp $$
 * @author:zhou
 * @date:2016/8/9 16:50
 */
public class DrugResult implements Serializable {
    private static final long serialVersionUID = -2637337896044509658L;
    @JSONField(name="Code")
    private String code;            //编号

    @JSONField(name="Name")
    private String name;            //名称

    @JSONField(name="Alias")
    private String alias;           //别名

    private String resultValue;     //药敏结果值(R/S/I)
    private String reference;       //参考值(药敏：K/B参考范围\真菌D：参考值)
    private String abnormalResult;  //异常结果值（异菌范围）
    private String orderNo;         //排序号
    private String resultCode;      //对应细菌结果序号
    private int method;             //MAC:0 KB:1

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAbnormalResult() {
        return abnormalResult;
    }

    public void setAbnormalResult(String abnormalResult) {
        this.abnormalResult = abnormalResult;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }
}
