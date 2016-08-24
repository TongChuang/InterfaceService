package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zcw on 2016/8/17.
 */
public class PatientRequestInfo implements Serializable{
    private static final long serialVersionUID = -6085747401931009371L;
    private Long requestId;         //申请记录序号
    private Long requestDetailId;   //申请明细序号
    private String patientCode;     //住院号（病历号）
    private String patientId;        //门诊唯一号
    private String patientRequestCode; //病人申请号码
    private String name;        //姓名
    private String sex;        //性别
    private String birthday;        //出生日期
    private String age;         //病人就诊年龄
    private String department;   //就诊科室
    private String ward;         //病区
    private String bedno;        //住院床号
    private String diagnose;     //临床诊断
    private String requestType; //申请类型 1 检验 2 检查
    private String requestItemType;//申请项目类型
    private String requestDoctor;        //申请医生
    private String requestDepartment;        //申请科室
    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    private Date requestDateTime;          //申请日期
    private String itemCode;                //项目代码
    private String itemName;                //项目名称
    private String itemPrintName;           //项目打印名称
    private float quantity;                 //数量
    private int status;                     //申请状态 -3 已退费 -1 撤销 3  确认 6 计费
    private String testDept;                //检验科室
    private int emergency;                  //是否急诊 1 急诊 0 否
    private float amount;                    //金额
    private String sampleType;              //标本类型
    private String testPart;                //检查部位名称
    private String patientFileCode;         //病人档案号

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getRequestDetailId() {
        return requestDetailId;
    }

    public void setRequestDetailId(Long requestDetailId) {
        this.requestDetailId = requestDetailId;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientRequestCode() {
        return patientRequestCode;
    }

    public void setPatientRequestCode(String patientRequestCode) {
        this.patientRequestCode = patientRequestCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestItemType() {
        return requestItemType;
    }

    public void setRequestItemType(String requestItemType) {
        this.requestItemType = requestItemType;
    }

    public String getRequestDoctor() {
        return requestDoctor;
    }

    public void setRequestDoctor(String requestDoctor) {
        this.requestDoctor = requestDoctor;
    }

    public String getRequestDepartment() {
        return requestDepartment;
    }

    public void setRequestDepartment(String requestDepartment) {
        this.requestDepartment = requestDepartment;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrintName() {
        return itemPrintName;
    }

    public void setItemPrintName(String itemPrintName) {
        this.itemPrintName = itemPrintName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTestDept() {
        return testDept;
    }

    public void setTestDept(String testDept) {
        this.testDept = testDept;
    }

    public int getEmergency() {
        return emergency;
    }

    public void setEmergency(int emergency) {
        this.emergency = emergency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getTestPart() {
        return testPart;
    }

    public void setTestPart(String testPart) {
        this.testPart = testPart;
    }

    public String getPatientFileCode() {
        return patientFileCode;
    }

    public void setPatientFileCode(String patientFileCode) {
        this.patientFileCode = patientFileCode;
    }
}
