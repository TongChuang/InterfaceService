package com.zcw.webservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcw.webservice.dao.HisInfoDao;
import com.zcw.webservice.dao.LisInfoDao;
import com.zcw.webservice.model.his.AccountItem;
import com.zcw.webservice.model.lis.SampleInfo;
import com.zcw.webservice.model.lis.SampleLog;
import com.zcw.webservice.model.lis.TestResult;
import com.zcw.webservice.model.vo.Report;
import com.zcw.webservice.server.LisInfoService;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

/**
 * Title:LisInfoServiceImpl
 * Description:WEBSERVICE
 *
 * @Author:zhou
 * @Date:2016/8/2 1:06
 * @Version:
 */
@WebService(endpointInterface = "com.zcw.webservice.server.LisInfoService",serviceName = "LisInfoService")
public class LisInfoServiceImpl extends SpringBeanAutowiringSupport implements LisInfoService {

    private static Logger log = Logger.getLogger(LisInfoServiceImpl.class);

    @Autowired
    private LisInfoDao lisInfoDao ;

    @Autowired
    private HisInfoDao hisInfoDao ;

    /**
     * 获取检验信息
     * @param barcode
     * @return
     */
    public String getTestInfo(String barcode) {
        JSONObject error = new JSONObject();
        if(barcode == null ||  barcode.equals("")){
            error.put("error","条码不能为空");
            return error.toJSONString();
        }
        try {
            return lisInfoDao.getTestInfo(barcode);
        } catch (Exception e) {
            e.printStackTrace();
            error.put("error",e.getMessage());
            return error.toJSONString();
        }
    }

    /**
     * 获取细菌信息列表
     * @return
     */
    public String getBacteriaList() {
        JSONObject obj = new JSONObject();
        try {
            return lisInfoDao.getBacteriaList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    /**
     * 获取检验目的列表
     * @return
     */
    public String getTestPurposeList() {
        JSONObject obj = new JSONObject();
        try {
            return lisInfoDao.getTestPurposeList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    /**
     * 获取药敏信息列表
     * @return
     */
    public String getDrugList() {
        JSONObject obj = new JSONObject();
        try {
            return lisInfoDao.getDrugList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    /**
     * 获取标本各类信息列表
     * @return
     */
    public String getSampleTypeList() {
        JSONObject obj = new JSONObject();
        try {
            return lisInfoDao.getSampleTypeList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    /**
     * 病人类别信息
     * @return
     */
    public String getPatientTypeList() {
        JSONObject obj = new JSONObject();
        try {
            return lisInfoDao.getPatientTypeList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    /**
     * 病区信息
     * @return
     */
    public String getWardList() {
        JSONObject obj = new JSONObject();
        try {
            return hisInfoDao.getWardList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    /**
     * 科室信息
     * @return
     */
    public String getDepartMentList() {
        JSONObject obj = new JSONObject();
        try {
            return hisInfoDao.getDepartmentList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    public String getSampleNo(String barcode) {
        JSONObject obj = new JSONObject();
        try {
            return hisInfoDao.getDepartmentList();
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    @Override
    public String getCollectedSampleList(String signStartDate, String signEndDate) {
        return null;
    }

    @Override
    public String getReceivedSampleList(String signStartDate, String signEndDate) {
        return null;
    }

    /**
     * 获取病人信息
     * @return
     */
    public String getPatientInfoList(String patientType,String patientCode) {
        JSONObject obj = new JSONObject();
        try {
            return hisInfoDao.getPatientInfo(patientType, patientCode);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("error",e.getMessage());
            return obj.toJSONString();
        }
    }

    /**
     *
     * @param report    检验结果信息
     * @return
     */
    public String saveTestResult(Report report) {
        log.info("saveTestResult================================START");
        log.info(JSON.toJSONString(report));
        log.info("saveTestResult================================END");
        return null;
    }

    public String saveSampleFlowLog(SampleLog sampleLog) {
        log.info("saveSampleFlowLog================================START");
        log.info(JSON.toJSONString(sampleLog));
        log.info("saveSampleFlowLog================================END");
        return null;
    }

    @Override
    public String returnSample(String reason, Date returnTime, String operator, SampleInfo sampleInfo) {
        log.info("returnSample================================START");
        log.info(JSON.toJSONString(reason));
        log.info(JSON.toJSONString(returnTime));
        log.info(JSON.toJSONString(operator));
        log.info(JSON.toJSONString(sampleInfo));
        log.info("returnSample================================END");
        return null;
    }

    @Override
    public String booking(AccountItem accountItem) {
        log.info("booking================================START");
        log.info(JSON.toJSONString(accountItem));
        log.info("booking================================END");
        return null;
    }
}
