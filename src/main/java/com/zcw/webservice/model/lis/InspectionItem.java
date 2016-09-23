package com.zcw.webservice.model.lis;

import java.io.Serializable;

/**
 * Created by zcw on 2016/9/21.
 *
 * 电子病历结果
 */
public class InspectionItem  implements Serializable{
    private static final long serialVersionUID = -6327081854619345941L;
    private String inspectionId;   //仪器代号+测定日期+样本编号(5位) ABL8002015122200008
    private String testItemId;          //项目编号
    private String testItemName_EN;     //项目英文名称
    private String getTestItemName_CN;  //项目名称
    private String unit;                //单位
    private String orderNum;            //序号
    private String reference;           //参考范围
    private String resultFlag;          //结果标记
    private String barcode;             //条码号
    private String testResult;          //结果

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getTestItemId() {
        return testItemId;
    }

    public void setTestItemId(String testItemId) {
        this.testItemId = testItemId;
    }

    public String getTestItemName_EN() {
        return testItemName_EN;
    }

    public void setTestItemName_EN(String testItemName_EN) {
        this.testItemName_EN = testItemName_EN;
    }

    public String getGetTestItemName_CN() {
        return getTestItemName_CN;
    }

    public void setGetTestItemName_CN(String getTestItemName_CN) {
        this.getTestItemName_CN = getTestItemName_CN;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
