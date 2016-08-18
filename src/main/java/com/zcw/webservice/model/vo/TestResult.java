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
 * @version \$Id TestResult.java, v 0.1 2016-08-09 17:09 zhou Exp $$
 * @author:zhou
 * @date:2016/8/9 17:09
 */
public class TestResult implements Serializable {
    private static final long serialVersionUID = 5683008316110610339L;
    @JSONField(name = "TestItemCode")
    private String testItemCode;    //检测项目编号

    @JSONField(name = "TestItemOrder")
    private String testItemOrder;   //检测项目排序号

    @JSONField(name = "ResultTypeId")
    private int resultTypeId;       //结果类型序号  阳性、药敏 :1 、其他:0

    @JSONField(name = "ResultType")
    private String resultType;      //结果类型：培养阴性(jc)、培养阳性(ya)、药敏(ym)、涂片(tp)、检测结果(jc)

    @JSONField(name = "Code")
    private String code;            //结果编号

    @JSONField(name = "Result")
    private String result;          //结果

    @JSONField(name = "DrugResistance")
    private String drugResistance;  //耐药标志(MRSA...)

    @JSONField(name = "Unit")
    private String unit;            //单位

    @JSONField(name = "AbnormalFlag")
    private String abnormalFlag;    //异常标志

    @JSONField(name = "Reference")
    private String reference;       //真菌D/内毒素 参考范围

    @JSONField(name = "Count")
    private String count;           //菌量计数

    public String getTestItemCode() {
        return testItemCode;
    }

    public void setTestItemCode(String testItemCode) {
        this.testItemCode = testItemCode;
    }

    public String getTestItemOrder() {
        return testItemOrder;
    }

    public void setTestItemOrder(String testItemOrder) {
        this.testItemOrder = testItemOrder;
    }

    public int getResultTypeId() {
        return resultTypeId;
    }

    public void setResultTypeId(int resultTypeId) {
        this.resultTypeId = resultTypeId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDrugResistance() {
        return drugResistance;
    }

    public void setDrugResistance(String drugResistance) {
        this.drugResistance = drugResistance;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAbnormalFlag() {
        return abnormalFlag;
    }

    public void setAbnormalFlag(String abnormalFlag) {
        this.abnormalFlag = abnormalFlag;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
