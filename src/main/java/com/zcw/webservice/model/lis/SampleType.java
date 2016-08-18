package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by zcw on 2016/8/10.
 */
public class SampleType implements Serializable {
    private static final long serialVersionUID = 2688482825396356720L;
    @JSONField(name = "Code")
    private String code;            //编号

    @JSONField(name = "Name")
    private String name;            //名称

    @JSONField(name = "Alias")
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
