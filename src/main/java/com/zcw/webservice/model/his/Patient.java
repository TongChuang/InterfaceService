package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Title: Patient
 * Description:病人信息
 *
 * @author:zhou
 * @date:2016/8/3 17:49
 * @version:
 */
public class Patient  implements Serializable {
    private static final long serialVersionUID = 565777468382249546L;
    @JsonProperty(value = "PatientCode")
    private String patientCode;     //住院号（病历号）

    @JsonProperty(value = "PatientUniqueId")
    private String parentId;    //门诊唯一号

    @JsonProperty(value = "ChargeType")
    private String chargeType;        //收费类别

    @JsonProperty(value = "Name")
    private String name;        //姓名

    @JsonProperty(value = "Sex")
    private String sex;        //性别

    @JsonProperty(value = "Birthday")
    private String birthday;        //出生日期

    @JsonProperty(value = "Age")
    private String age;        //病人就诊年龄

    @JsonProperty(value = "Department")
    private String department;        //就诊科室

    @JsonProperty(value = "Doctor")
    private String doctor;        //就诊医生

    @JsonProperty(value = "Company")
    private String company;        //单位信息

    @JsonProperty(value = "IdCard")
    private String IdCard;        //身份证号码

    @JsonProperty(value = "AdmissionDepartment")
    private String admissionDepartment;   //入院科室

    @JsonProperty(value = "HospitalWard")
    private String hospitalWard;        //入院病区

    @JsonProperty(value = "InpatientDepartment")
    private String inpatientDepartment; //住院科室

    @JsonProperty(value = "InpatientWard")
    private String InpatientWard;        //住院病区

    @JsonProperty(value = "Bedno")
    private String bedno;        //住院床号

    @JsonProperty(value = "Status")
    private String status;        //住院状态 1-取消登记 ；0-已预约；1-尚未入科；2-已经入科；3-已预出院；4-已出院

    @JsonProperty(value = "PatientType")
    private String patientType;        //病人类别 医保类别

    @JsonProperty(value = "PatientFileCode")
    private String patientFileCode;        //病人档案编号

    public String getPatientFileCode() {
        return patientFileCode;
    }

    public void setPatientFileCode(String patientFileCode) {
        this.patientFileCode = patientFileCode;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getAdmissionDepartment() {
        return admissionDepartment;
    }

    public void setAdmissionDepartment(String admissionDepartment) {
        this.admissionDepartment = admissionDepartment;
    }

    public String getHospitalWard() {
        return hospitalWard;
    }

    public void setHospitalWard(String hospitalWard) {
        this.hospitalWard = hospitalWard;
    }

    public String getInpatientDepartment() {
        return inpatientDepartment;
    }

    public void setInpatientDepartment(String inpatientDepartment) {
        this.inpatientDepartment = inpatientDepartment;
    }

    public String getInpatientWard() {
        return InpatientWard;
    }

    public void setInpatientWard(String inpatientWard) {
        InpatientWard = inpatientWard;
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }
}
