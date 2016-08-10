package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Title: TestInfo
 * Description:检验信息
 *
 * @Author:zhou
 * @Date:2016/8/3 10:07
 * @Version:
 */
public class TestInfo {

    @JSONField(name = "Barcode")
    private String barcode; //申请单号

    @JSONField(name = "PatientTypeCode")
    private String patientTypeCode;//病人种类编码

    @JSONField(name = "PatientTypeName")
    private String patientTypeName;//病人种类名称

    @JSONField(name = "PatientCode")
    private String patientCode;//病人编号

    @JSONField(name = "PatientName")
    private String patientName;//病人姓名

    @JSONField(name = "PatientSex")
    private String patientSex;//病人性别

    @JSONField(name = "PatientAge")
    private String patientAge;//病人年龄

    @JSONField(name = "PatientAgeUnit")
    private String patientAgeUnit;//年龄单位

    @JSONField(name = "DepartmentCode")
    private String departmentCode;//科室编码

    @JSONField(name = "DepartmentName")
    private String departmentName;//科室名称

    @JSONField(name = "InpatientAreaCode")
    private String inpatientAreaCode;//病区编码

    @JSONField(name = "InpatientAreaName")
    private String inpatientAreaName;//病区名称

    @JSONField(name = "BedNo")
    private String bedNo;//床号

    @JSONField(name = "DoctorCode")
    private String doctorCode;//医生编码

    @JSONField(name = "DoctorName")
    private String doctorName;//医生姓名

    @JSONField(name = "SpecimenTypeCode")
    private String specimenTypeCode;//标本类型编码

    @JSONField(name = "SpecimenTypeName")
    private String specimenTypeName;//标本类型名称

    @JSONField(name = "ApplyDate")
    private String applyDate;//申请日期

    @JSONField(name = "CollectDate")
    private String collectDate;//采集日期

    @JSONField(name = "CollectAccount")
    private String collectAccount;//采集人姓名

    @JSONField(name = "ChargeTypeCode")
    private String chargeTypeCode;//费用类别编码

    @JSONField(name = "ChargeTypeName")
    private String chargeTypeName;//费用类别名称

    @JSONField(name = "Diagnosis")
    private String diagnosis;//临床诊断

    @JSONField(name = "SignDate")
    private String signDate;//签收时间

    @JSONField(name = "SignerAccount")
    private String signerAccount;//签收人账号

    @JSONField(name = "TestItems")
    private List<TestItem> testItems; //检验项目

    @JSONField(name = "Remark")
    private String remark;          //备注

    @JSONField(name = "SampleNo")
    private String sampleNo;        //标本号

    @JSONField(name = "IsToll")
    private String isToll;  //是否收费 1：已收 2：未收

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
     * Getter method for property <tt>patientTypeCode</tt>.
     *
     * @return property value of patientTypeCode
     */
    public String getPatientTypeCode() {
        return patientTypeCode;
    }

    /**
     * Setter method for property <tt>patientTypeCode</tt>.
     *
     * @param patientTypeCode value to be assigned to property patientTypeCode
     */
    public void setPatientTypeCode(String patientTypeCode) {
        this.patientTypeCode = patientTypeCode;
    }

    /**
     * Getter method for property <tt>patientTypeName</tt>.
     *
     * @return property value of patientTypeName
     */
    public String getPatientTypeName() {
        return patientTypeName;
    }

    /**
     * Setter method for property <tt>patientTypeName</tt>.
     *
     * @param patientTypeName value to be assigned to property patientTypeName
     */
    public void setPatientTypeName(String patientTypeName) {
        this.patientTypeName = patientTypeName;
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
     * Getter method for property <tt>patientSex</tt>.
     *
     * @return property value of patientSex
     */
    public String getPatientSex() {
        return patientSex;
    }

    /**
     * Setter method for property <tt>patientSex</tt>.
     *
     * @param patientSex value to be assigned to property patientSex
     */
    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    /**
     * Getter method for property <tt>patientAge</tt>.
     *
     * @return property value of patientAge
     */
    public String getPatientAge() {
        return patientAge;
    }

    /**
     * Setter method for property <tt>patientAge</tt>.
     *
     * @param patientAge value to be assigned to property patientAge
     */
    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    /**
     * Getter method for property <tt>patientAgeUnit</tt>.
     *
     * @return property value of patientAgeUnit
     */
    public String getPatientAgeUnit() {
        return patientAgeUnit;
    }

    /**
     * Setter method for property <tt>patientAgeUnit</tt>.
     *
     * @param patientAgeUnit value to be assigned to property patientAgeUnit
     */
    public void setPatientAgeUnit(String patientAgeUnit) {
        this.patientAgeUnit = patientAgeUnit;
    }

    /**
     * Getter method for property <tt>departmentCode</tt>.
     *
     * @return property value of departmentCode
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * Setter method for property <tt>departmentCode</tt>.
     *
     * @param departmentCode value to be assigned to property departmentCode
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * Getter method for property <tt>departmentName</tt>.
     *
     * @return property value of departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Setter method for property <tt>departmentName</tt>.
     *
     * @param departmentName value to be assigned to property departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Getter method for property <tt>inpatientAreaCode</tt>.
     *
     * @return property value of inpatientAreaCode
     */
    public String getInpatientAreaCode() {
        return inpatientAreaCode;
    }

    /**
     * Setter method for property <tt>inpatientAreaCode</tt>.
     *
     * @param inpatientAreaCode value to be assigned to property inpatientAreaCode
     */
    public void setInpatientAreaCode(String inpatientAreaCode) {
        this.inpatientAreaCode = inpatientAreaCode;
    }

    /**
     * Getter method for property <tt>inpatientAreaName</tt>.
     *
     * @return property value of inpatientAreaName
     */
    public String getInpatientAreaName() {
        return inpatientAreaName;
    }

    /**
     * Setter method for property <tt>inpatientAreaName</tt>.
     *
     * @param inpatientAreaName value to be assigned to property inpatientAreaName
     */
    public void setInpatientAreaName(String inpatientAreaName) {
        this.inpatientAreaName = inpatientAreaName;
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
     * Getter method for property <tt>doctorCode</tt>.
     *
     * @return property value of doctorCode
     */
    public String getDoctorCode() {
        return doctorCode;
    }

    /**
     * Setter method for property <tt>doctorCode</tt>.
     *
     * @param doctorCode value to be assigned to property doctorCode
     */
    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    /**
     * Getter method for property <tt>doctorName</tt>.
     *
     * @return property value of doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Setter method for property <tt>doctorName</tt>.
     *
     * @param doctorName value to be assigned to property doctorName
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * Getter method for property <tt>specimenTypeCode</tt>.
     *
     * @return property value of specimenTypeCode
     */
    public String getSpecimenTypeCode() {
        return specimenTypeCode;
    }

    /**
     * Setter method for property <tt>specimenTypeCode</tt>.
     *
     * @param specimenTypeCode value to be assigned to property specimenTypeCode
     */
    public void setSpecimenTypeCode(String specimenTypeCode) {
        this.specimenTypeCode = specimenTypeCode;
    }

    /**
     * Getter method for property <tt>specimenTypeName</tt>.
     *
     * @return property value of specimenTypeName
     */
    public String getSpecimenTypeName() {
        return specimenTypeName;
    }

    /**
     * Setter method for property <tt>specimenTypeName</tt>.
     *
     * @param specimenTypeName value to be assigned to property specimenTypeName
     */
    public void setSpecimenTypeName(String specimenTypeName) {
        this.specimenTypeName = specimenTypeName;
    }

    /**
     * Getter method for property <tt>applyDate</tt>.
     *
     * @return property value of applyDate
     */
    public String getApplyDate() {
        return applyDate;
    }

    /**
     * Setter method for property <tt>applyDate</tt>.
     *
     * @param applyDate value to be assigned to property applyDate
     */
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * Getter method for property <tt>collectDate</tt>.
     *
     * @return property value of collectDate
     */
    public String getCollectDate() {
        return collectDate;
    }

    /**
     * Setter method for property <tt>collectDate</tt>.
     *
     * @param collectDate value to be assigned to property collectDate
     */
    public void setCollectDate(String collectDate) {
        this.collectDate = collectDate;
    }

    /**
     * Getter method for property <tt>collectAccount</tt>.
     *
     * @return property value of collectAccount
     */
    public String getCollectAccount() {
        return collectAccount;
    }

    /**
     * Setter method for property <tt>collectAccount</tt>.
     *
     * @param collectAccount value to be assigned to property collectAccount
     */
    public void setCollectAccount(String collectAccount) {
        this.collectAccount = collectAccount;
    }

    /**
     * Getter method for property <tt>chargeTypeCode</tt>.
     *
     * @return property value of chargeTypeCode
     */
    public String getChargeTypeCode() {
        return chargeTypeCode;
    }

    /**
     * Setter method for property <tt>chargeTypeCode</tt>.
     *
     * @param chargeTypeCode value to be assigned to property chargeTypeCode
     */
    public void setChargeTypeCode(String chargeTypeCode) {
        this.chargeTypeCode = chargeTypeCode;
    }

    /**
     * Getter method for property <tt>chargeTypeName</tt>.
     *
     * @return property value of chargeTypeName
     */
    public String getChargeTypeName() {
        return chargeTypeName;
    }

    /**
     * Setter method for property <tt>chargeTypeName</tt>.
     *
     * @param chargeTypeName value to be assigned to property chargeTypeName
     */
    public void setChargeTypeName(String chargeTypeName) {
        this.chargeTypeName = chargeTypeName;
    }

    /**
     * Getter method for property <tt>diagnosis</tt>.
     *
     * @return property value of diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Setter method for property <tt>diagnosis</tt>.
     *
     * @param diagnosis value to be assigned to property diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Getter method for property <tt>signDate</tt>.
     *
     * @return property value of signDate
     */
    public String getSignDate() {
        return signDate;
    }

    /**
     * Setter method for property <tt>signDate</tt>.
     *
     * @param signDate value to be assigned to property signDate
     */
    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    /**
     * Getter method for property <tt>signerAccount</tt>.
     *
     * @return property value of signerAccount
     */
    public String getSignerAccount() {
        return signerAccount;
    }

    /**
     * Setter method for property <tt>signerAccount</tt>.
     *
     * @param signerAccount value to be assigned to property signerAccount
     */
    public void setSignerAccount(String signerAccount) {
        this.signerAccount = signerAccount;
    }

    /**
     * Getter method for property <tt>testItems</tt>.
     *
     * @return property value of testItems
     */
    public List<TestItem> getTestItems() {
        return testItems;
    }

    /**
     * Setter method for property <tt>testItems</tt>.
     *
     * @param testItems value to be assigned to property testItems
     */
    public void setTestItems(List<TestItem> testItems) {
        this.testItems = testItems;
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
     * Getter method for property <tt>isToll</tt>.
     *
     * @return property value of isToll
     */
    public String getIsToll() {
        return isToll;
    }

    /**
     * Setter method for property <tt>isToll</tt>.
     *
     * @param isToll value to be assigned to property isToll
     */
    public void setIsToll(String isToll) {
        this.isToll = isToll;
    }
}
