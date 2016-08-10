package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: DrugResult
 * Description: 药敏
 *
 * @Author:zhou
 * @Date:2016/8/2 15:19
 * @Version:
 */
public class Drug {

    @JSONField(name="Code")
    private String code;            //编号

    @JSONField(name="Name")
    private String name;            //名称

    @JSONField(name="Alias")
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
