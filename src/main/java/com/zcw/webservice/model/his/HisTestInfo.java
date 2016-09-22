package com.zcw.webservice.model.his;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zcw on 2016/9/21.
 */
public class HisTestInfo implements Serializable{

    private static final long serialVersionUID = -4338894017252534684L;

    private HisSampleInfo sampleInfo;

    private List<HisTestResult> testResultList;

    public HisSampleInfo getSampleInfo() {
        return sampleInfo;
    }

    public void setSampleInfo(HisSampleInfo sampleInfo) {
        this.sampleInfo = sampleInfo;
    }

    public List<HisTestResult> getTestResultList() {
        return testResultList;
    }

    public void setTestResultList(List<HisTestResult> testResultList) {
        this.testResultList = testResultList;
    }
}
