/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.lis;

import org.eclipse.jetty.util.thread.ThreadPool;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Title: TestResultDetail
 * Description:检验结果信息<br><br>
 *
 *  TestItem testItem;      检验项目<br>
 * String sampleNo;        样本编号<br>
 * String sampleType;      样本类型<br>
 * String deviceId;        仪器ID<br>
 * Date mesureTime;        仪器时间<br>
 * String operator;        操作人<br>
 * String unit;            单位<br>
 * int resultTypeId;       结果类型序号<br>
 * String resultType;      结果类型：培养阴性(jc)、培养阳性(ym)、药敏(yi)、涂片(tp)、检测结果(jc)<br>
 * String result;          结果<br>
 * String abnormalResult;  异常结果值（异菌范围）<br>
 * String colonyCount;     菌落计数<br>
 * String resultValue;     药敏结果值(R/S/I)<br>
 * String reference;       参考值(药敏：K/B参考范围\真菌D：参考值)<br>
 * String drugResistance;  耐药标志(MRSA..)<br>
 *
 *
 * @version  TestResultDetail.java, v 0.1 2016-08-08 9:43 zhou
 * @author:zhou
 * @date:2016/8/8 9:43
 *
 */
public class TestResultDetail implements Serializable {
    private static final long serialVersionUID = -6266573065041893117L;

    public TestResultDetail() {
    }

    public TestResultDetail(List<TestItem> testItem, String sampleNo, String sampleType, String deviceId, Date mesureTime, String operator, String unit, String resultType, String result, String abnormalResult, String colonyCount, String resultValue, String reference, String drugResistance,int resultTypeId) {
        this.testItem = testItem;
        this.sampleNo = sampleNo;
        this.sampleType = sampleType;
        this.deviceId = deviceId;
        this.mesureTime = mesureTime;
        this.operator = operator;
        this.unit = unit;
        this.resultType = resultType;
        this.result = result;
        this.abnormalResult = abnormalResult;
        this.colonyCount = colonyCount;
        this.resultValue = resultValue;
        this.reference = reference;
        this.drugResistance = drugResistance;
        this.resultTypeId = resultTypeId;
    }

    //private String testItemId;      //检验项目ID
    //private String testItemName;    //检验项目名称
    private List<TestItem> testItem;  //检验项目
    private String sampleNo;        //样本编号
    private String sampleType;      //样本类型
    private String deviceId;        //仪器ID
    private Date mesureTime;        //仪器时间
    private String operator;        //操作人
    private String unit;            //单位
    private int resultTypeId;       //结果类型序号
    private String resultType;      //结果类型：培养阴性(jc)、培养阳性(ym)、药敏(yi)、涂片(tp)、检测结果(jc)
    private String result;          //结果
    private String abnormalResult;  //异常结果值（异菌范围）
    private String colonyCount;     //菌落计数
    private String resultValue;     //药敏结果值(R/S/I)
    private String reference;       //参考值(药敏：K/B参考范围\真菌D：参考值)
    private String drugResistance;  //耐药标志(MRSA\)

    /**
     * Getter method for property <tt>resultTypeId</tt>.
     *
     * @return property value of resultTypeId
     */
    public int getResultTypeId() {
        return resultTypeId;
    }

    /**
     * Setter method for property <tt>resultTypeId</tt>.
     *
     * @param resultTypeId value to be assigned to property resultTypeId
     */
    public void setResultTypeId(int resultTypeId) {
        this.resultTypeId = resultTypeId;
    }

    /**
     * Getter method for property <tt>resultValue</tt>.
     *
     * @return property value of resultValue
     */
    public String getResultValue() {
        return resultValue;
    }

    /**
     * Setter method for property <tt>resultValue</tt>.
     *
     * @param resultValue value to be assigned to property resultValue
     */
    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    /**
     * Getter method for property <tt>serialVersionUID</tt>.
     *
     * @return property value of serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Getter method for property <tt>testItem</tt>.
     *
     * @return property value of testItem
     */
    public List<TestItem> getTestItem() {
        return testItem;
    }

    /**
     * Setter method for property <tt>testItem</tt>.
     *
     * @param testItem value to be assigned to property testItem
     */
    public void setTestItem(List<TestItem>  testItem) {
        this.testItem = testItem;
    }

    /**
     * Getter method for property <tt>sampleNo</tt>.
     *
     * @return property value of sampleNo
     */
    public String getSampleNo() {
        return sampleNo;
    }

    /**
     * Setter method for property <tt>sampleNo</tt>.
     *
     * @param sampleNo value to be assigned to property sampleNo
     */
    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    /**
     * Getter method for property <tt>sampleType</tt>.
     *
     * @return property value of sampleType
     */
    public String getSampleType() {
        return sampleType;
    }

    /**
     * Setter method for property <tt>sampleType</tt>.
     *
     * @param sampleType value to be assigned to property sampleType
     */
    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    /**
     * Getter method for property <tt>deviceId</tt>.
     *
     * @return property value of deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Setter method for property <tt>deviceId</tt>.
     *
     * @param deviceId value to be assigned to property deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Getter method for property <tt>mesureTime</tt>.
     *
     * @return property value of mesureTime
     */
    public Date getMesureTime() {
        return mesureTime;
    }

    /**
     * Setter method for property <tt>mesureTime</tt>.
     *
     * @param mesureTime value to be assigned to property mesureTime
     */
    public void setMesureTime(Date mesureTime) {
        this.mesureTime = mesureTime;
    }

    /**
     * Getter method for property <tt>operator</tt>.
     *
     * @return property value of operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Setter method for property <tt>operator</tt>.
     *
     * @param operator value to be assigned to property operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Getter method for property <tt>unit</tt>.
     *
     * @return property value of unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Setter method for property <tt>unit</tt>.
     *
     * @param unit value to be assigned to property unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Getter method for property <tt>resultType</tt>.
     *
     * @return property value of resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * Setter method for property <tt>resultType</tt>.
     *
     * @param resultType value to be assigned to property resultType
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * Getter method for property <tt>result</tt>.
     *
     * @return property value of result
     */
    public String getResult() {
        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     *
     * @param result value to be assigned to property result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Getter method for property <tt>abnormalResult</tt>.
     *
     * @return property value of abnormalResult
     */
    public String getAbnormalResult() {
        return abnormalResult;
    }

    /**
     * Setter method for property <tt>abnormalResult</tt>.
     *
     * @param abnormalResult value to be assigned to property abnormalResult
     */
    public void setAbnormalResult(String abnormalResult) {
        this.abnormalResult = abnormalResult;
    }

    /**
     * Getter method for property <tt>colonyCount</tt>.
     *
     * @return property value of colonyCount
     */
    public String getColonyCount() {
        return colonyCount;
    }

    /**
     * Setter method for property <tt>colonyCount</tt>.
     *
     * @param colonyCount value to be assigned to property colonyCount
     */
    public void setColonyCount(String colonyCount) {
        this.colonyCount = colonyCount;
    }

    /**
     * Getter method for property <tt>reference</tt>.
     *
     * @return property value of reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Setter method for property <tt>reference</tt>.
     *
     * @param reference value to be assigned to property reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Getter method for property <tt>drugResistance</tt>.
     *
     * @return property value of drugResistance
     */
    public String getDrugResistance() {
        return drugResistance;
    }

    /**
     * Setter method for property <tt>drugResistance</tt>.
     *
     * @param drugResistance value to be assigned to property drugResistance
     */
    public void setDrugResistance(String drugResistance) {
        this.drugResistance = drugResistance;
    }
}
