package com.zcw.webservice.model.his;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcw.webservice.common.JsonDateFormateFull;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zcw on 2016/9/20.
 * @author zcw
 *
 *
 */
public class HisSampleInfo implements Serializable{

    private static final long serialVersionUID = 4285837296297398078L;

    private String barCode;                 //病人医嘱序号
    private String sampleNo;                //样本号
    private String organizationId;          //组织ID
    private int patientType;             //病人类型 Not null	1 门诊 2 住院 3 其他
    private String patientId;               //病人就诊ID
    private String patientNo;               //住院号（病历号）
    private String patientName;              //病人姓名
    private int sex;
    private int age;
    private String ageUnit;
    private int isBaby;             //0 成人，1 婴儿
    private String bedNo;           //床号
    private String diagnosisId;     //临床诊断代码
    private String diagnosis;       //临床诊断名称
    private String part;            //部位
    private String cycleId;         //生理周期ID
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date executeTime;       //送检时间(采集时间)
    private String requesterId;     //开单医生ID
    private String requesterName;   //开单医生名称
    private String departmentId;    //开单科室ID
    private String departmentName;  //开单科室名称
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date receiveTime;        //样本接收时间
    private String testerId;        //执行人员ID
    private String testerName;      //执行人员姓名
    private String testDepartmentId;    //执行科室ID
    private String testDepartmentName;    //执行科室名称
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date   testTime;            //执行时间
    private String auditerId;           //审核人员ID
    private String auditerName;         //审核人员名称
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date auditTime;           //审核时间
    private String auditNote;           //审核备注
    private String sampleTypeId;        //样本类型ID
    private String sampleTypeName;      //样本类型名称
    private int sampleOperateStatus;    //样本操作状态 0
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date sampleResultTime;            //样本结果时间
    private int sampleResultStatus;     //样本结果状态
    private int isPrint;                //是否打印判别
    private int isEmergency;            //是否急诊判别
    private String testId;              //检测目的ID
    private String testName;            //检测目的名称
    private String reportUrl;           //报告单路径地址
    private String patientCode;         //病人档案编号

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public int getPatientType() {
        return patientType;
    }

    public void setPatientType(int patientType) {
        this.patientType = patientType;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public int getIsBaby() {
        return isBaby;
    }

    public void setIsBaby(int isBaby) {
        this.isBaby = isBaby;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getTesterId() {
        return testerId;
    }

    public void setTesterId(String testerId) {
        this.testerId = testerId;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public String getTestDepartmentId() {
        return testDepartmentId;
    }

    public void setTestDepartmentId(String testDepartmentId) {
        this.testDepartmentId = testDepartmentId;
    }

    public String getTestDepartmentName() {
        return testDepartmentName;
    }

    public void setTestDepartmentName(String testDepartmentName) {
        this.testDepartmentName = testDepartmentName;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getAuditerId() {
        return auditerId;
    }

    public void setAuditerId(String auditerId) {
        this.auditerId = auditerId;
    }

    public String getAuditerName() {
        return auditerName;
    }

    public void setAuditerName(String auditerName) {
        this.auditerName = auditerName;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditNote() {
        return auditNote;
    }

    public void setAuditNote(String auditNote) {
        this.auditNote = auditNote;
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

    public int getSampleOperateStatus() {
        return sampleOperateStatus;
    }

    public void setSampleOperateStatus(int sampleOperateStatus) {
        this.sampleOperateStatus = sampleOperateStatus;
    }

    public Date getSampleResultTime() {
        return sampleResultTime;
    }

    public void setSampleResultTime(Date sampleResultTime) {
        this.sampleResultTime = sampleResultTime;
    }

    public int getSampleResultStatus() {
        return sampleResultStatus;
    }

    public void setSampleResultStatus(int sampleResultStatus) {
        this.sampleResultStatus = sampleResultStatus;
    }

    public int getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(int isPrint) {
        this.isPrint = isPrint;
    }

    public int getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(int isEmergency) {
        this.isEmergency = isEmergency;
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

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }
}
