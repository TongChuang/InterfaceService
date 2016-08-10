/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: TestItem
 * Description:检验项目
 *
 * @version  TestItem.java, v0.1 2016-08-05 15:02 zhou
 * @author:zhou
 * @date:2016/8/5 15:02
 */
public class TestItem {

    @JSONField(name = "Id")
    private String id;      //检验项目ID

    @JSONField(name = "Code")
    private String code;    //检验项目编号

    @JSONField(name = "Name")
    private String name;    //检验项目名称

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }
}
