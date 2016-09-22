package com.zcw.webservice.model.his;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcw.webservice.common.JsonDateFormateFull;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zcw on 2016/9/21.
 */
public class HisTestResult implements Serializable {

    private static final long serialVersionUID = 3110465778797920114L;

    private String testItemId;              //检测项目ID
    private String testItemName_EN;         //项目英文名称
    private String testItemName_CN;         //项目中文名称
    private String sampleTypeId;            //样本类型ID
    private String sampleTypeName;          //样本类型名称
    private String testResult;              //结果
    private String resultFlag;              //结果标志
    private String resultHint;              //结果提示
    private String unit;
    private String referenceLo;             //下限
    private String referenceHi;             //上限
    private String reference;               //参考范围
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date resultTime;              //结果操作时间
    private int order;                      //结果顺序
    private String method;                  //检测方法

    public String getTestItemId() {
        return testItemId;
    }

    public void setTestItemId(String testItemId) {
        this.testItemId = testItemId;
    }

    public String getTestItemName_EN() {
        return testItemName_EN;
    }

    public void setTestItemName_EN(String testItemName_EN) {
        this.testItemName_EN = testItemName_EN;
    }

    public String getTestItemName_CN() {
        return testItemName_CN;
    }

    public void setTestItemName_CN(String testItemName_CN) {
        this.testItemName_CN = testItemName_CN;
    }

    public String getSampleTypeId() {
        return sampleTypeId;
    }

    public void setSampleTypeId(String sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
    }

    public String getSampleTypeName() {
        return sampleTypeName;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getResultHint() {
        return resultHint;
    }

    public void setResultHint(String resultHint) {
        this.resultHint = resultHint;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getReferenceLo() {
        return referenceLo;
    }

    public void setReferenceLo(String referenceLo) {
        this.referenceLo = referenceLo;
    }

    public String getReferenceHi() {
        return referenceHi;
    }

    public void setReferenceHi(String referenceHi) {
        this.referenceHi = referenceHi;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
