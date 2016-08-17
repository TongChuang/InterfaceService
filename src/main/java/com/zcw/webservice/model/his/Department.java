package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Title: Department
 * Description:科室
 *
 * @Author:zhou
 * @Date:2016/8/3 14:26
 * @Version:
 */
public class Department  implements Serializable {

    private static final long serialVersionUID = 4728135225309467951L;
    @JsonProperty(value = "Id")
    private String id;          //ID

    @JsonProperty(value = "ParentId")
    private String parentId;    //父级ID

    @JsonProperty(value = "Code")
    private String code;        //编号

    @JsonProperty(value = "Name")
    private String name;        //名称

    @JsonProperty(value = "Department")
    private String department;  //所属科室

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
