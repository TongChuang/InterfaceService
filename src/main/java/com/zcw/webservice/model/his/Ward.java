package com.zcw.webservice.model.his;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Title: Ward
 * Description:病区信息
 *
 * @Author:zhou
 * @Date:2016/8/3 12:59
 * @Version:
 */
public class Ward  implements Serializable {

    private static final long serialVersionUID = 4878790668776478543L;
    @JsonProperty(value = "Id")
    private String id;          //ID

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
