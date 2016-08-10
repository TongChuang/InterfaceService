/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.vo;

/**
 * Title: .IntelliJ IDEA
 * Description:
 *
 * @version \$Id TestResult.java, v 0.1 2016-08-09 17:09 zhou Exp $$
 * @author:zhou
 * @date:2016/8/9 17:09
 */
public class TestResult {
    private String testItemCode;    //检测项目编号
    private String testItemOrder;   //检测项目排序号
    private int resultTypeId;       //结果类型序号
    private String resultType;      //结果类型：培养阴性(jc)、培养阳性(ym)、药敏(yi)、涂片(tp)、检测结果(jc)
    private String code;            //结果编号
    private String result;          //结果
    private String drugResistance;  //耐药标志(MRSA...)
    private String unit;            //单位

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
}
