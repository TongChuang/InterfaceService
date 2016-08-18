package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Title: TestPurpose
 * Description:检验目的
 *
 * @Author:zhou
 * @Date:2016/8/3 10:04
 * @Version:
 */
public class TestPurpose  implements Serializable {
    private static final long serialVersionUID = -3231507851521340081L;
    @JSONField(name = "Code")
    private String code;                //编号

    @JSONField(name = "Name")
    private String name;                //名称

    @JSONField(name = "Fee")
    private String fee;                 //费用;

    @JSONField(name = "Alias")
    private String alias;               //别名;

    @JSONField(name = "SampleId")
    private String sampleId;           //标本ID;

    @JSONField(name = "SampleName")
    private String sampleName;          //标本名称;

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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }
}
