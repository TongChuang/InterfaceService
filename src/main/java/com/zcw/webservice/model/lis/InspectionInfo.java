package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcw.webservice.common.JsonDateFormateFull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zcw on 2016/9/29.
 *
 * 电子病历样本信息
 */
public class InspectionInfo implements Serializable {
    private static final long serialVersionUID = 6736097683305695052L;
    private String inspectionId;
    private String patientId;       //就诊ID

    private String patientNo;       //住院号、门诊号

    private String patientName;     //病人姓名

    private String sex;                //性别

    private String age;             //年龄

    private String ageUnit;         //年龄类型

    private String department;      //部门

    private String wardName;        //病区

    private String bedNo;           //床位号

    private String sampleType;      //样本类型

    private String sampleTypeName;  //样本类型名称

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date testTime;          //检测时间

    private Long testerHisId;       //检验者HISID

    private String testerName;      //检验者名称

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date requestTime;       //申请时间

    private String requestName;     //申请医生

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date receiveTime;       //接收时间

    private String  receiveName;    //接收人

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date reportTime;        //报告时间

    private String reportName;      //报告 人

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date auditTime;         //审核时间

    private String auditName;       //审核人
    private String testId;          //检验目的ID
    private String testName;        //检验目的名称
    private String barcode;         //条码号（医嘱号）
    private String sampleNo;        //样本号


    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Long getTesterHisId() {
        return testerHisId;
    }

    public void setTesterHisId(Long testerHisId) {
        this.testerHisId = testerHisId;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleTypeName() {
        return sampleTypeName;
    }

    public void setSampleTypeName(String sampleTypeName) {
        this.sampleTypeName = sampleTypeName;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
