package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by zcw on 2016/8/10.
 */
public class PatientType implements Serializable {

    private static final long serialVersionUID = 1028021596329452815L;

    @JsonProperty(value = "Code")
    private String code;            //编号

    @JsonProperty(value = "Name")
    private String name;            //名称

    @JSONField(name="Alias")
    @JsonProperty(value = "Alias")
    private String alias;           //别名

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
