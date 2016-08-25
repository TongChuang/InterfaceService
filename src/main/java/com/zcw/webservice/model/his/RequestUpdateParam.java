package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;

import javax.ws.rs.PathParam;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zcw on 2016/8/17.
 */
public class RequestUpdateParam  implements Serializable{
    private static final long serialVersionUID = -2021077548820602298L;


    @JSONField(name = "RequestType")
    int  requestType;       //项目申请类型 11 门诊检验  12 门诊检查 21 住院检验  22 住院检查

    @JSONField(name = "ItemId")
    String itemId;          //项目明细ID   多个用“|”分隔

    @JSONField(name = "ExeType")
    int exeType;            //执行状态  1 执行(门诊)  2 取消执行(门诊)  3 接受计费(住院) 4 退费(住院)  5 打印 (住院)  6 取消打印7 预约时间

    @JSONField(name = "ExeDeptCode")
    String  exeDeptCode;    //执行科室

    @JSONField(name = "ExeDeptName")
    String exeDeptName;

    @JSONField(name = "ExeDoctorCode")
    String exeDoctorCode;   //执行医生

    @JSONField(name = "ExeDoctorName")
    String exeDoctorName;

    @JSONField(name = "ExeDate",format="yyyy-MM-dd HH:mm:ss")
    Date exeDate;           //执行时间 (预约时间)

    @JSONField(name = "Expand")
    String expand;          //扩展内容  打印时打印编号、预约注意事项

    public RequestUpdateParam(int requestType, String itemId, int exeType, String exeDeptCode, String exeDeptName, String exeDoctorCode, String exeDoctorName, Date exeDate, String expand) {
        this.requestType = requestType;
        this.itemId = itemId;
        this.exeType = exeType;
        this.exeDeptCode = exeDeptCode;
        this.exeDeptName = exeDeptName;
        this.exeDoctorCode = exeDoctorCode;
        this.exeDoctorName = exeDoctorName;
        this.exeDate = exeDate;
        this.expand = expand;
    }

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

    public Date getExeDate() {
        return exeDate;
    }

    public void setExeDate(Date exeDate) {
        this.exeDate = exeDate;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }
}
