/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.lis;

import java.io.Serializable;
import java.util.List;

/**
 * Title: TestResult
 * Description:报告结果信息
 *
 * SampleInfo sampleInfo;  //标本信息
 * List<TestResultDetail> testResultDetailList;    //结果信息
 *
 * @version  TestResult.java, v 0.1 2016-08-08 10:33 zhou
 * @author:zhou
 * @date:2016/8/8 10:33
 */
public class TestResult  implements Serializable {
    private static final long serialVersionUID = 4720976893579075173L;
    private SampleInfo sampleInfo;  //标本信息
    private List<TestResultDetail> testResultDetailList;    //结果信息

    public TestResult() {
    }

    public TestResult(SampleInfo sampleInfo, List<TestResultDetail> testResultDetailList) {
        this.sampleInfo = sampleInfo;
        this.testResultDetailList = testResultDetailList;
    }

    /**
     * Getter method for property <tt>sampleInfo</tt>.
     *
     * @return property value of sampleInfo
     */
    public SampleInfo getSampleInfo() {
        return sampleInfo;
    }

    /**
     * Setter method for property <tt>sampleInfo</tt>.
     *
     * @param sampleInfo value to be assigned to property sampleInfo
     */
    public void setSampleInfo(SampleInfo sampleInfo) {
        this.sampleInfo = sampleInfo;
    }

    /**
     * Getter method for property <tt>testResultDetailList</tt>.
     *
     * @return property value of testResultDetailList
     */
    public List<TestResultDetail> getTestResultDetailList() {
        return testResultDetailList;
    }

    /**
     * Setter method for property <tt>testResultDetailList</tt>.
     *
     * @param testResultDetailList value to be assigned to property testResultDetailList
     */
    public void setTestResultDetailList(List<TestResultDetail> testResultDetailList) {
        this.testResultDetailList = testResultDetailList;
    }
}
