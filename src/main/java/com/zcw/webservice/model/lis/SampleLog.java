/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.lis;

import java.util.Date;

/**
 * Title: SampleLog
 * Description:标本流转记录
 *
 * @version  SampleLog.java, v 0.1 2016-08-08 11:38 zhou
 * @author:zhou
 * @date:2016/8/8 11:38
 */
public class SampleLog {
    private String sysName;         //系统名称
    private String sampleNo;        //样本编号
    private Date recordTime;        //记录时间
    private String operatorNo;    //操作人工号
    private String operatorName;  //操作人姓名
    private String remark;        //操作内容

    /**
     * Getter method for property <tt>sysName</tt>.
     *
     * @return property value of sysName
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * Setter method for property <tt>sysName</tt>.
     *
     * @param sysName value to be assigned to property sysName
     */
    public void setSysName(String sysName) {
        this.sysName = sysName;
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
     * Getter method for property <tt>recordTime</tt>.
     *
     * @return property value of recordTime
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * Setter method for property <tt>recordTime</tt>.
     *
     * @param recordTime value to be assigned to property recordTime
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
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

    /**
     * Getter method for property <tt>operatorName</tt>.
     *
     * @return property value of operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * Setter method for property <tt>operatorName</tt>.
     *
     * @param operatorName value to be assigned to property operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     *
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     *
     * @param remark value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
