/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.zcw.webservice.model.lis.SampleInfo;
import com.zcw.webservice.model.lis.TestItem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Title: .IntelliJ IDEA
 * Description:
 *
 * @version \$Id Report.java, v 0.1 2016-08-09 16:53 zhou Exp $$
 * @author:zhou
 * @date:2016/8/9 16:53
 */
public class Report implements Serializable {
    private static final long serialVersionUID = -2923351759486827410L;

    @JSONField(name = "SampleInfo")
    private SampleInfo sampleInfo;

    @JSONField(name = "Results")
    private List<TestResult> results;       //结果信息

    @JSONField(name = "DrugResults")
    private List<DrugResult> drugResults;   //结果信息

    @JSONField(name = "ReportType")
    private int reportType;                 //报告类型 培养类型报告:0 真菌D/内毒素:1
    public SampleInfo getSampleInfo() {
        return sampleInfo;
    }

    public void setSampleInfo(SampleInfo sampleInfo) {
        this.sampleInfo = sampleInfo;
    }

    public List<TestResult> getResults() {
        return results;
    }

    public void setResults(List<TestResult> results) {
        this.results = results;
    }

    public List<DrugResult> getDrugResults() {
        return drugResults;
    }

    public void setDrugResults(List<DrugResult> drugResults) {
        this.drugResults = drugResults;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


}
