/**
 * 杭州同烁信息技术有限公司
 * Copyright (C) 2004-2016 All Rights Reserved.
 */
package com.zcw.webservice.model.vo;

import com.zcw.webservice.model.lis.SampleInfo;
import com.zcw.webservice.model.lis.TestItem;

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
public class Report {
    private SampleInfo sampleInfo;
    private List<TestResult> results;       //结果信息
    private List<DrugResult> drugResults;   //结果信息

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
}
