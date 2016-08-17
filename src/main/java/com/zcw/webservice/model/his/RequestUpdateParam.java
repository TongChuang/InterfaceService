package com.zcw.webservice.model.his;

/**
 * Created by zcw on 2016/8/17.
 */
public class RequestUpdateParam {
    int  requestType;
    String itemId;
    int exeType;
    String  exeDeptCode;
    String exeDeptName;
    String exeDoctorCode;
    String exeDoctorName;
    String exeDate;
    String expand;

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getExeType() {
        return exeType;
    }

    public void setExeType(int exeType) {
        this.exeType = exeType;
    }

    public String getExeDeptCode() {
        return exeDeptCode;
    }

    public void setExeDeptCode(String exeDeptCode) {
        this.exeDeptCode = exeDeptCode;
    }

    public String getExeDeptName() {
        return exeDeptName;
    }

    public void setExeDeptName(String exeDeptName) {
        this.exeDeptName = exeDeptName;
    }

    public String getExeDoctorCode() {
        return exeDoctorCode;
    }

    public void setExeDoctorCode(String exeDoctorCode) {
        this.exeDoctorCode = exeDoctorCode;
    }

    public String getExeDoctorName() {
        return exeDoctorName;
    }

    public void setExeDoctorName(String exeDoctorName) {
        this.exeDoctorName = exeDoctorName;
    }

    public String getExeDate() {
        return exeDate;
    }

    public void setExeDate(String exeDate) {
        this.exeDate = exeDate;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }
}
