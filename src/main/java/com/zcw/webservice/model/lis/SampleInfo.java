/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcw.webservice.common.JsonDateFormateFull;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: SampleInfo
 * Description:标本信息
 *
 * @version  SampleInfo.java, v 0.1 2016-08-08 9:47 zhou
 * @author:zhou
 * @date:2016/8/8 9:47
 */
public class SampleInfo  implements Serializable {
    private static final long serialVersionUID = 3168127784272973173L;
    @JSONField(name = "Barcode")
    private String barcode;             //条码号

    @JSONField(name = "SampleId")
    private String sampleId;             //样本号

    @JSONField(name = "PatientCode")
    private String patientCode;         //住院号、门诊号

    @JSONField(name = "PatientId")
    private String patientId;           //就诊卡号（唯一号、病历号会重复）

    @JSONField(name = "TestDateTime",format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date testDateTime;          //测定日期

    @JSONField(name = "PatientName")
    private String patientName;         //病人姓名

    @JSONField(name = "Sex")
    private String sex;                 //性别

    @JSONField(name = "Age")
    private String age;                 //年龄

    @JSONField(name = "AgeType")
    private String ageType;             //年龄单位(类型)

    @JSONField(name = "DepartmentCode")
    private String departmentCode;      //病人就诊科室代码

    @JSONField(name = "Department")
    private String department;          //病人就诊科室

    @JSONField(name = "BedNo")
    private String bedNo;               //床号

    @JSONField(name = "SampleTypeCode")
    private String sampleTypeCode;      //标本类型代码

    @JSONField(name = "SampleType")
    private String sampleType;          //标本类型

    @JSONField(name = "SampleStatus")
    private String sampleStatus;        //标本状态

    @JSONField(name = "SamplingTime",format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date samplingTime;          //采样时间

    @JSONField(name = "ClinicalDiagnosis")
    private String clinicalDiagnosis;   //临床诊断

    @JSONField(name = "HosSectionCode")
    private String hosSectionCode;      //送检科室代码

    @JSONField(name = "HosSection")
    private String hosSection;          //送检科室

    @JSONField(name = "InspectCode")
    private String inspectCode;         //送检医生代码

    @JSONField(name = "InspectDoctor")
    private String inspectDoctor;       //送检医生名称

    @JSONField(name = "TestDoctorCode")
    private String testDoctorCode;      //检验医生

    @JSONField(name = "TestDoctor")
    private String testDoctor;          //检验医生

    @JSONField(name = "OperatorCode")
    private String operatorCode;        //操作人

    @JSONField(name = "Operator")
    private String operator;            //操作人

    @JSONField(name = "OperateTime",format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date operateTime;           //操作时间

    @JSONField(name = "AuditDoctorCode")
    private String auditDoctorCode;     //审核医生

    @JSONField(name = "AuditDoctor")
    private String auditDoctor;         //审核医生

    @JSONField(name = "AuditTime",format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date auditTime;             //审核时间

    @JSONField(name = "ReportDoctorCode")
    private String reportDoctorCode;    //报告医生

    @JSONField(name = "ReportDoctor")
    private String reportDoctor;        //报告医生

    @JSONField(name ="ReportDateTime" ,format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date reportDateTime;        //报告日期

    @JSONField(name = "TestDestinationNo")
    private String testDestinationNo;   //检验目的编号

    @JSONField(name = "TestDestinationName")
    private String testDestinationName; //检验目的名称

    @JSONField(name = "Birthday")
    private String birthday;            //出生日期

    @JSONField(name = "SamplingPart")
    private String samplingPart;        //采集部位

    @JSONField(name = "SampleNote")
    private String sampleNote;          //标本性状

    @JSONField(name = "SampleDescription")
    private String sampleDescription;   //标本描述

    @JSONField(name = "Count")
    private String count;               //采集数量

    @JSONField(name = "PatientTypeCode")
    private String patientTypeCode;      //病人类型申请方式:普通、急诊

    @JSONField(name = "PatientTypeName")
    private String patientTypeName;      //病人类型申请方式:普通、急诊

    @JSONField(name = "Fee")
    private Double fee;                 //费用

    @JSONField(name = "FeeStatus")
    private String feeStatus;           //收费状态

    @JSONField(name = "Cycle")
    private String cycle;               //生理周期

    @JSONField(name = "InvoiceNum")
    private String invoiceNum;          //发票号

    @JSONField(name = "SectionId")
    private String sectionId;           //实验室部门

    @JSONField(name = "Section")
    private String section;             //实验室部门

    @JSONField(name = "PatientFileNo")
    private String patientFileNo;       //病人档案号

    @JSONField(name = "BillDepartmentCode")
    private String billDepartmentCode;  //开单科室编号

    @JSONField(name = "BillDepartment")
    private String billDepartment;      //开单科室

    @JSONField(name = "PatientPhone")
    private String patientPhone;        //病人联系电话

    /*
    * 固定字段无需传送
    * */
    private String paperSize;           //纸张格式      A5
    private Date createTime;            //创建时间      now
    private String deviceId;            //设备ID      微生物

    public String getSampleTypeCode() {
        return sampleTypeCode;
    }

    public void setSampleTypeCode(String sampleTypeCode) {
        this.sampleTypeCode = sampleTypeCode;
    }

    public String getPatientTypeCode() {
        return patientTypeCode;
    }

    public void setPatientTypeCode(String patientTypeCode) {
        this.patientTypeCode = patientTypeCode;
    }

    public String getPatientTypeName() {
        return patientTypeName;
    }

    public void setPatientTypeName(String patientTypeName) {
        this.patientTypeName = patientTypeName;
    }

    /**
     * Getter method for property <tt>barcode</tt>.
     *
     * @return property value of barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Setter method for property <tt>barcode</tt>.
     *
     * @param barcode value to be assigned to property barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * Getter method for property <tt>sampleId</tt>.
     *
     * @return property value of sampleId
     */
    public String getSampleId() {
        return sampleId;
    }

    /**
     * Setter method for property <tt>sampleId</tt>.
     *
     * @param sampleId value to be assigned to property sampleId
     */
    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    /**
     * Getter method for property <tt>patientId</tt>.
     *
     * @return property value of patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Setter method for property <tt>patientId</tt>.
     *
     * @param patientId value to be assigned to property patientId
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Getter method for property <tt>testdateTime</tt>.
     *
     * @return property value of testdateTime
     */
    public Date getTestDateTime() {
        return testDateTime;
    }

    /**
     * Setter method for property <tt>testdateTime</tt>.
     *
     * @param testDateTime value to be assigned to property testdateTime
     */
    public void setTestDateTime(Date testDateTime) {
        this.testDateTime = testDateTime;
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
     * Getter method for property <tt>sex</tt>.
     *
     * @return property value of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter method for property <tt>sex</tt>.
     *
     * @param sex value to be assigned to property sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter method for property <tt>age</tt>.
     *
     * @return property value of age
     */
    public String getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Getter method for property <tt>ageType</tt>.
     *
     * @return property value of ageType
     */
    public String getAgeType() {
        return ageType;
    }

    /**
     * Setter method for property <tt>ageType</tt>.
     *
     * @param ageType value to be assigned to property ageType
     */
    public void setAgeType(String ageType) {
        this.ageType = ageType;
    }

    /**
     * Getter method for property <tt>department</tt>.
     *
     * @return property value of department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Setter method for property <tt>department</tt>.
     *
     * @param department value to be assigned to property department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Getter method for property <tt>bedNo</tt>.
     *
     * @return property value of bedNo
     */
    public String getBedNo() {
        return bedNo;
    }

    /**
     * Setter method for property <tt>bedNo</tt>.
     *
     * @param bedNo value to be assigned to property bedNo
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
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
     * Getter method for property <tt>sampleStatus</tt>.
     *
     * @return property value of sampleStatus
     */
    public String getSampleStatus() {
        return sampleStatus;
    }

    /**
     * Setter method for property <tt>sampleStatus</tt>.
     *
     * @param sampleStatus value to be assigned to property sampleStatus
     */
    public void setSampleStatus(String sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    /**
     * Getter method for property <tt>samplingTime</tt>.
     *
     * @return property value of samplingTime
     */
    public Date getSamplingTime() {
        return samplingTime;
    }

    /**
     * Setter method for property <tt>samplingTime</tt>.
     *
     * @param samplingTime value to be assigned to property samplingTime
     */
    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    /**
     * Getter method for property <tt>clinicalDiagnosis</tt>.
     *
     * @return property value of clinicalDiagnosis
     */
    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    /**
     * Setter method for property <tt>clinicalDiagnosis</tt>.
     *
     * @param clinicalDiagnosis value to be assigned to property clinicalDiagnosis
     */
    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    /**
     * Getter method for property <tt>hosSection</tt>.
     *
     * @return property value of hosSection
     */
    public String getHosSection() {
        return hosSection;
    }

    /**
     * Setter method for property <tt>hosSection</tt>.
     *
     * @param hosSection value to be assigned to property hosSection
     */
    public void setHosSection(String hosSection) {
        this.hosSection = hosSection;
    }

    /**
     * Getter method for property <tt>inspectDoctor</tt>.
     *
     * @return property value of inspectDoctor
     */
    public String getInspectDoctor() {
        return inspectDoctor;
    }

    /**
     * Setter method for property <tt>inspectDoctor</tt>.
     *
     * @param inspectDoctor value to be assigned to property inspectDoctor
     */
    public void setInspectDoctor(String inspectDoctor) {
        this.inspectDoctor = inspectDoctor;
    }

    /**
     * Getter method for property <tt>testDoctor</tt>.
     *
     * @return property value of testDoctor
     */
    public String getTestDoctor() {
        return testDoctor;
    }

    /**
     * Setter method for property <tt>testDoctor</tt>.
     *
     * @param testDoctor value to be assigned to property testDoctor
     */
    public void setTestDoctor(String testDoctor) {
        this.testDoctor = testDoctor;
    }

    /**
     * Getter method for property <tt>auditDoctor</tt>.
     *
     * @return property value of auditDoctor
     */
    public String getAuditDoctor() {
        return auditDoctor;
    }

    /**
     * Setter method for property <tt>auditDoctor</tt>.
     *
     * @param auditDoctor value to be assigned to property auditDoctor
     */
    public void setAuditDoctor(String auditDoctor) {
        this.auditDoctor = auditDoctor;
    }

    /**
     * Getter method for property <tt>testDestinationNo</tt>.
     *
     * @return property value of testDestinationNo
     */
    public String getTestDestinationNo() {
        return testDestinationNo;
    }

    /**
     * Setter method for property <tt>testDestinationNo</tt>.
     *
     * @param testDestinationNo value to be assigned to property testDestinationNo
     */
    public void setTestDestinationNo(String testDestinationNo) {
        this.testDestinationNo = testDestinationNo;
    }

    /**
     * Getter method for property <tt>testDestinationName</tt>.
     *
     * @return property value of testDestinationName
     */
    public String getTestDestinationName() {
        return testDestinationName;
    }

    /**
     * Setter method for property <tt>testDestinationName</tt>.
     *
     * @param testDestinationName value to be assigned to property testDestinationName
     */
    public void setTestDestinationName(String testDestinationName) {
        this.testDestinationName = testDestinationName;
    }

    /**
     * Getter method for property <tt>reportDateTime</tt>.
     *
     * @return property value of reportDateTime
     */
    public Date getReportDateTime() {
        return reportDateTime;
    }

    /**
     * Setter method for property <tt>reportDateTime</tt>.
     *
     * @param reportDateTime value to be assigned to property reportDateTime
     */
    public void setReportDateTime(Date reportDateTime) {
        this.reportDateTime = reportDateTime;
    }

    /**
     * Getter method for property <tt>birthday</tt>.
     *
     * @return property value of birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Setter method for property <tt>birthday</tt>.
     *
     * @param birthday value to be assigned to property birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter method for property <tt>samplingPart</tt>.
     *
     * @return property value of samplingPart
     */
    public String getSamplingPart() {
        return samplingPart;
    }

    /**
     * Setter method for property <tt>samplingPart</tt>.
     *
     * @param samplingPart value to be assigned to property samplingPart
     */
    public void setSamplingPart(String samplingPart) {
        this.samplingPart = samplingPart;
    }

    /**
     * Getter method for property <tt>sampleNote</tt>.
     *
     * @return property value of sampleNote
     */
    public String getSampleNote() {
        return sampleNote;
    }

    /**
     * Setter method for property <tt>sampleNote</tt>.
     *
     * @param sampleNote value to be assigned to property sampleNote
     */
    public void setSampleNote(String sampleNote) {
        this.sampleNote = sampleNote;
    }

    /**
     * Getter method for property <tt>sampleDescription</tt>.
     *
     * @return property value of sampleDescription
     */
    public String getSampleDescription() {
        return sampleDescription;
    }

    /**
     * Setter method for property <tt>sampleDescription</tt>.
     *
     * @param sampleDescription value to be assigned to property sampleDescription
     */
    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    /**
     * Getter method for property <tt>count</tt>.
     *
     * @return property value of count
     */
    public String getCount() {
        return count;
    }

    /**
     * Setter method for property <tt>count</tt>.
     *
     * @param count value to be assigned to property count
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * Getter method for property <tt>fee</tt>.
     *
     * @return property value of fee
     */
    public Double getFee() {
        return fee;
    }

    /**
     * Setter method for property <tt>fee</tt>.
     *
     * @param fee value to be assigned to property fee
     */
    public void setFee(Double fee) {
        this.fee = fee;
    }

    /**
     * Getter method for property <tt>feeStatus</tt>.
     *
     * @return property value of feeStatus
     */
    public String getFeeStatus() {
        return feeStatus;
    }

    /**
     * Setter method for property <tt>feeStatus</tt>.
     *
     * @param feeStatus value to be assigned to property feeStatus
     */
    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

    /**
     * Getter method for property <tt>cycle</tt>.
     *
     * @return property value of cycle
     */
    public String getCycle() {
        return cycle;
    }

    /**
     * Setter method for property <tt>cycle</tt>.
     *
     * @param cycle value to be assigned to property cycle
     */
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    /**
     * Getter method for property <tt>invoiceNum</tt>.
     *
     * @return property value of invoiceNum
     */
    public String getInvoiceNum() {
        return invoiceNum;
    }

    /**
     * Setter method for property <tt>invoiceNum</tt>.
     *
     * @param invoiceNum value to be assigned to property invoiceNum
     */
    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    /**
     * Getter method for property <tt>sectionId</tt>.
     *
     * @return property value of sectionId
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * Setter method for property <tt>sectionId</tt>.
     *
     * @param sectionId value to be assigned to property sectionId
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * Getter method for property <tt>patientFileNo</tt>.
     *
     * @return property value of patientFileNo
     */
    public String getPatientFileNo() {
        return patientFileNo;
    }

    /**
     * Setter method for property <tt>patientFileNo</tt>.
     *
     * @param patientFileNo value to be assigned to property patientFileNo
     */
    public void setPatientFileNo(String patientFileNo) {
        this.patientFileNo = patientFileNo;
    }

    /**
     * Getter method for property <tt>billDepartment</tt>.
     *
     * @return property value of billDepartment
     */
    public String getBillDepartment() {
        return billDepartment;
    }

    /**
     * Setter method for property <tt>billDepartment</tt>.
     *
     * @param billDepartment value to be assigned to property billDepartment
     */
    public void setBillDepartment(String billDepartment) {
        this.billDepartment = billDepartment;
    }

    /**
     * Getter method for property <tt>patientPhone</tt>.
     *
     * @return property value of patientPhone
     */
    public String getPatientPhone() {
        return patientPhone;
    }

    /**
     * Setter method for property <tt>patientPhone</tt>.
     *
     * @param patientPhone value to be assigned to property patientPhone
     */
    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    /**
     * Getter method for property <tt>paperSize</tt>.
     *
     * @return property value of paperSize
     */
    public String getPaperSize() {
        return paperSize;
    }

    /**
     * Setter method for property <tt>paperSize</tt>.
     *
     * @param paperSize value to be assigned to property paperSize
     */
    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    /**
     * Getter method for property <tt>createTime</tt>.
     *
     * @return property value of createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Setter method for property <tt>createTime</tt>.
     *
     * @param createTime value to be assigned to property createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getReportDoctor() {
        return reportDoctor;
    }

    public void setReportDoctor(String reportDoctor) {
        this.reportDoctor = reportDoctor;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getHosSectionCode() {
        return hosSectionCode;
    }

    public void setHosSectionCode(String hosSectionCode) {
        this.hosSectionCode = hosSectionCode;
    }

    public String getInspectCode() {
        return inspectCode;
    }

    public void setInspectCode(String inspectCode) {
        this.inspectCode = inspectCode;
    }

    public String getTestDoctorCode() {
        return testDoctorCode;
    }

    public void setTestDoctorCode(String testDoctorCode) {
        this.testDoctorCode = testDoctorCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getAuditDoctorCode() {
        return auditDoctorCode;
    }

    public void setAuditDoctorCode(String auditDoctorCode) {
        this.auditDoctorCode = auditDoctorCode;
    }

    public String getReportDoctorCode() {
        return reportDoctorCode;
    }

    public void setReportDoctorCode(String reportDoctorCode) {
        this.reportDoctorCode = reportDoctorCode;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getBillDepartmentCode() {
        return billDepartmentCode;
    }

    public void setBillDepartmentCode(String billDepartmentCode) {
        this.billDepartmentCode = billDepartmentCode;
    }
}
