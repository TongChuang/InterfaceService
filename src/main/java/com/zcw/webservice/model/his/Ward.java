package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: Ward
 * Description:病区信息
 *
 * @Author:zhou
 * @Date:2016/8/3 12:59
 * @Version:
 */
public class Ward {

    @JSONField(name = "Id")
    private String id;          //ID

    @JSONField(name = "Code")
    private String code;        //编号

    @JSONField(name = "Name")
    private String name;        //名称

    @JSONField(name = "Department")
    private String department;  //所属科室

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
