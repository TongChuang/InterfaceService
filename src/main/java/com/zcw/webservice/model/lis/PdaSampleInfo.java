package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcw.webservice.common.JsonDateFormateFull;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zcw on 2016/9/27.
 * pda样本执行信息
 */
public class PdaSampleInfo implements Serializable {
    private static final long serialVersionUID = 7905336970013766541L;
    private String patientId;        //病人ID
    private String patientNo;        //就诊号
    private String barcode;          //条码号
    private String itemId;           //项目ID
    private String itemName;         //项目名称

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date reportTime;        //报告时间
    private String reportName;      //报告人


    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date executeTime;       //执行时间
    private String executeName;     //执行人


    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date sendTime;          //发送时间
    private String sendName;        //发送人


    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date requestTime;       //申请日期
    private String requestName;     //申请人

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = JsonDateFormateFull.class)
    private Date receiveTime;       //接收时间
    private String receiveName;     //接收人
    private int reportType;         //类型
    private int reportStatus;       //状态
    private String wardId;         //病区ID
    private String wardName;        //病区名称

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteName() {
        return executeName;
    }

    public void setExecuteName(String executeName) {
        this.executeName = executeName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
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

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getWardId() {
        return wardId;
    }

    public void setSardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

}
