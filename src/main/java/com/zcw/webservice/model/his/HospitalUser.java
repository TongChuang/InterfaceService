package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Title: HospitalUser
 * Description:医院用户
 *
 * Created by yuzh on 2016/10/8.
 */
public class HospitalUser implements Serializable {

    @JSONField(name = "Id")
    private String id;          //医院用户HIS ID

    @JSONField(name = "WorkId")
    private String workId;    //医院工号

    @JSONField(name = "Name")
    private String name;        //名姓名

    @JSONField(name = "Phone")
    private String phone;       //电话

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
