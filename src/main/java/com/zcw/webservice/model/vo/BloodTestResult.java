package com.zcw.webservice.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import net.sourceforge.jtds.jdbc.DateTime;

import java.io.Serializable;
import java.util.Date;

/** 输血结果接口
 * Created by zcw on 2016/10/11.
 */
public class BloodTestResult implements Serializable {

    private static final long serialVersionUID = 8091100002471743456L;
    @JSONField(name = "PatientId")
    private String patientId;

    @JSONField(name = "PatientCode")
    private String patientCode;

    @JSONField(name = "SampleNo")
    private String sampleNo;

    @JSONField(name = "TestItemCode")
    private String testItemCode;    //检测项目编号

    @JSONField(name = "TestItemName")
    private String testItemName;    //检测项目名称

    @JSONField(name = "TestItemOrder")
    private String testItemOrder;   //检测项目排序号

    @JSONField(name = "Result")
    private String result;          //结果

    @JSONField(name = "TestTime",format = "yyyy-MM-dd hh:mm:ss")
    private Date testTime;

    public String getTestItemCode() {
        return testItemCode;
    }

    public void setTestItemCode(String testItemCode) {
        this.testItemCode = testItemCode;
    }

    public String getTestItemName() {
        return testItemName;
    }

    public void setTestItemName(String testItemName) {
        this.testItemName = testItemName;
    }

    public String getTestItemOrder() {
        return testItemOrder;
    }

    public void setTestItemOrder(String testItemOrder) {
        this.testItemOrder = testItemOrder;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }
}
