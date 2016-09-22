/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcw.webservice.common.JsonDateFormateFull;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: AccountItem2
 * Description:收费相关信息
 *
 * @version  AccountItem2.java, v 0.1 2016-08-08 16:15 zhou
 * @author:zhou
 * @date:2016/8/8 16:15
 */
public class AccountItem  implements Serializable {

    private static final long serialVersionUID = -4040025415801341888L;

    public AccountItem() {
        super();
    }

    public AccountItem(String patientCode, String patientId, String patientType, String patientName, String testPurposesCode, String testPurposes, Date dateTime, int quantity, double price, String feeItemCode, String feeItemName, String billingDoctorNo, String billingDeptNo, String testDoctorNo, String testDoctorDeptNo, String operatorNo, Long accountId) {
        this.patientCode = patientCode;
        this.patientId = patientId;
        this.patientType = patientType;
        this.patientName = patientName;
        this.testPurposesCode = testPurposesCode;
        this.testPurposes = testPurposes;
        this.dateTime = dateTime;
        this.quantity = quantity;
        this.price = price;
        this.feeItemCode = feeItemCode;
        this.feeItemName = feeItemName;
        this.billingDoctorNo = billingDoctorNo;
        this.billingDeptNo = billingDeptNo;
        this.testDoctorNo = testDoctorNo;
        this.testDoctorDeptNo = testDoctorDeptNo;
        this.operatorNo = operatorNo;
        this.accountId = accountId;
    }

    @JSONField(name = "patientCode")
    private String patientCode;     //病人住院序号
    @JSONField(name = "PatientId")
    private String patientId;       //病人就诊ID
    @JSONField(name = "patientType")
    private String patientType;     //病人类型 住院 1、门诊 2
    @JSONField(name = "patientName")
    private String patientName;     //病人姓名
    @JSONField(name = "testPurposesCode")
    private String testPurposesCode;  //检验目的编号
    @JSONField(name = "testPurposes")
    private String testPurposes;    //检验目的
    @JSONField(name = "DateTime",format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date dateTime;        //费用发生日期
    @JSONField(name = "Quantity")
    private int quantity;           //费用发生数量
    @JSONField(name = "Price")
    private double price;           //费用发生单价
    @JSONField(name = "FeeItemCode")
    protected String feeItemCode;     //费用项目序号
    @JSONField(name = "FeeItemName")
    protected String feeItemName;     //费用项目名称

    //private String costSource;    //费用途径序号    12 用血 14 LIS 15 物资
    @JSONField(name = "BillingDoctorNo")
    private String billingDoctorNo;	//开单医生序号
    @JSONField(name = "BillingDeptNo")
    private String billingDeptNo;	//开单科室序号
    @JSONField(name = "TestDoctorNo")
    private String testDoctorNo;	//执行用户序号
    @JSONField(name = "TestDoctorDeptNo")
    private String testDoctorDeptNo;//执行科室序号
    @JSONField(name = "OperatorNo")
    private String operatorNo;	    //操作用户序号
    @JSONField(name = "AccountId")
    private Long accountId;         //记账序号

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    /**
     * Getter method for property <tt>patientCode</tt>.
     *
     * @return property value of patientCode
     */
    public String getPatientCode() {
        return patientCode;
    }

    /**
     * Setter method for property <tt>patientCode</tt>.
     *
     * @param patientCode value to be assigned to property patientCode
     */
    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    /**
     * Getter method for property <tt>patientName</tt>.
     *
     * @return property value of patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Setter method for property <tt>patientName</tt>.
     *
     * @param patientName value to be assigned to property patientName
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * Getter method for property <tt>testPurposes</tt>.
     *
     * @return property value of testPurposes
     */
    public String getTestPurposes() {
        return testPurposes;
    }

    /**
     * Setter method for property <tt>testPurposes</tt>.
     *
     * @param testPurposes value to be assigned to property testPurposes
     */
    public void setTestPurposes(String testPurposes) {
        this.testPurposes = testPurposes;
    }

    /**
     * Getter method for property <tt>dateTime</tt>.
     *
     * @return property value of dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Setter method for property <tt>dateTime</tt>.
     *
     * @param dateTime value to be assigned to property dateTime
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Getter method for property <tt>quantity</tt>.
     *
     * @return property value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter method for property <tt>quantity</tt>.
     *
     * @param quantity value to be assigned to property quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter method for property <tt>price</tt>.
     *
     * @return property value of price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method for property <tt>price</tt>.
     *
     * @param price value to be assigned to property price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter method for property <tt>billingDoctorNo</tt>.
     *
     * @return property value of billingDoctorNo
     */
    public String getBillingDoctorNo() {
        return billingDoctorNo;
    }

    /**
     * Setter method for property <tt>billingDoctorNo</tt>.
     *
     * @param billingDoctorNo value to be assigned to property billingDoctorNo
     */
    public void setBillingDoctorNo(String billingDoctorNo) {
        this.billingDoctorNo = billingDoctorNo;
    }

    /**
     * Getter method for property <tt>billingDeptNo</tt>.
     *
     * @return property value of billingDeptNo
     */
    public String getBillingDeptNo() {
        return billingDeptNo;
    }

    /**
     * Setter method for property <tt>billingDeptNo</tt>.
     *
     * @param billingDeptNo value to be assigned to property billingDeptNo
     */
    public void setBillingDeptNo(String billingDeptNo) {
        this.billingDeptNo = billingDeptNo;
    }

    /**
     * Getter method for property <tt>testDoctorNo</tt>.
     *
     * @return property value of testDoctorNo
     */
    public String getTestDoctorNo() {
        return testDoctorNo;
    }

    /**
     * Setter method for property <tt>testDoctorNo</tt>.
     *
     * @param testDoctorNo value to be assigned to property testDoctorNo
     */
    public void setTestDoctorNo(String testDoctorNo) {
        this.testDoctorNo = testDoctorNo;
    }

    /**
     * Getter method for property <tt>testDoctorDeptNo</tt>.
     *
     * @return property value of testDoctorDeptNo
     */
    public String getTestDoctorDeptNo() {
        return testDoctorDeptNo;
    }

    /**
     * Setter method for property <tt>testDoctorDeptNo</tt>.
     *
     * @param testDoctorDeptNo value to be assigned to property testDoctorDeptNo
     */
    public void setTestDoctorDeptNo(String testDoctorDeptNo) {
        this.testDoctorDeptNo = testDoctorDeptNo;
    }

    /**
     * Getter method for property <tt>operatorNo</tt>.
     *
     * @return property value of operatorNo
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    /**
     * Setter method for property <tt>operatorNo</tt>.
     *
     * @param operatorNo value to be assigned to property operatorNo
     */
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getTestPurposesCode() {
        return testPurposesCode;
    }

    public void setTestPurposesCode(String testPurposesCode) {
        this.testPurposesCode = testPurposesCode;
    }

    public String getFeeItemCode() {
        return feeItemCode;
    }

    public void setFeeItemCode(String feeItemCode) {
        this.feeItemCode = feeItemCode;
    }

    public String getFeeItemName() {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName) {
        this.feeItemName = feeItemName;
    }
}
