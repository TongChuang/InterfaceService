package com.zcw.webservice.impl;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.dao.HisInfoDao;
import com.zcw.webservice.dao.LisInfoDao;
import com.zcw.webservice.model.his.AccountItem;
import com.zcw.webservice.model.lis.SampleLog;
import com.zcw.webservice.model.vo.Report;
import com.zcw.webservice.model.vo.ReturnMsg;
import com.zcw.webservice.server.LisInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;
import java.util.Date;

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
        ReturnMsg msg = new ReturnMsg();
        if(barcode == null ||  barcode.equals("")){
            msg.setState(0);
            msg.setMessage("条码号不存在");
            return JSON.toJSONString(msg);
        }
        try {
            msg.setState(1);
            msg.setInfo(lisInfoDao.getTestInfo(barcode));
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 获取细菌信息列表
     * @return
     */
    public String getBacteriaList() {
        log.info("getBacteriaList================================START");
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(lisInfoDao.getBacteriaList());
        } catch (Exception e) {
            log.error("获取细菌信息列表异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 获取检验目的列表
     * @return
     */
    public String getTestPurposeList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(lisInfoDao.getTestPurposeList());
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 获取药敏信息列表
     * @return
     */
    public String getDrugList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(lisInfoDao.getDrugList());
        } catch (Exception e) {
            log.error("获取药敏信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 获取标本各类信息列表
     * @return
     */
    public String getSampleTypeList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(lisInfoDao.getSampleTypeList());
        } catch (Exception e) {
            log.error("获取标本类型信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 病人类别信息
     * @return
     */
    public String getPatientTypeList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(lisInfoDao.getPatientTypeList());
        } catch (Exception e) {
            log.error("获取病人类型信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 病区信息
     * @return
     */
    public String getWardList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(hisInfoDao.getWardList());
        } catch (Exception e) {
            log.error("获取病区信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 科室信息
     * @return
     */
    public String getDepartMentList() {
        ReturnMsg msg = new ReturnMsg();;
        try {
            msg.setState(1);
            msg.setInfo(hisInfoDao.getDepartmentList());
        } catch (Exception e) {
            log.error("获取科室信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 获取样本号
     * @param barcode
     * @return
     */
    public String getSampleNo(String barcode) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(hisInfoDao.getDepartmentList());
        } catch (Exception e) {
            log.error("获取获取样本号信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String getCollectedSampleList(String signStartDate, String signEndDate) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo("");
        } catch (Exception e) {
            log.error("获取获取样本号信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * LIS已签收标本信息
     *
     * @param signStartDate
     * @param signEndDate
     * @return
     */
    public String getReceivedSampleList(String signStartDate, String signEndDate) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(lisInfoDao.getReceivedSampleList(signStartDate,signEndDate));
        } catch (Exception e) {
            log.error("获取获取样本号信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     * 获取病人信息
     * @return
     */
    public String getPatientInfoList(String patientType,String patientCode) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setInfo(hisInfoDao.getPatientInfo(patientType, patientCode));
        } catch (Exception e) {
            log.error("获取获取样本号信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg);
    }

    /**
     *
     * @param report    检验结果信息
     * @return
     */
    public String saveTestResult(Report report) {
        log.info("saveTestResult================================START");
        log.info(JSON.toJSONString(report));
        System.out.println(JSON.toJSONString(report));
        ReturnMsg msg = new ReturnMsg();
        try{
            msg = lisInfoDao.saveTestResult(report);
        }catch (Exception e){
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        System.out.println(JSON.toJSONString(msg));
        log.info("saveTestResult================================END");

        return JSON.toJSONString(msg);
    }

    /**
     * 记录标本流转日志
     * @param sampleLog 标本日志
     * @return
     */
    public String saveSampleFlowLog(SampleLog sampleLog) {
        ReturnMsg msg = new ReturnMsg();
        log.info("saveSampleFlowLog================================START");
        log.info(JSON.toJSONString(sampleLog));
        log.info("saveSampleFlowLog================================END");
        return JSON.toJSONString(msg);
    }


    public String returnSample(String reason, Date returnTime, String operator,String barcode) {
        ReturnMsg msg = new ReturnMsg();
        log.info("returnSample================================START");
        log.info(JSON.toJSONString(reason));
        log.info(JSON.toJSONString(returnTime));
        log.info(JSON.toJSONString(operator));
        log.info(JSON.toJSONString(barcode));
        log.info("returnSample================================END");
        return JSON.toJSONString(msg);
    }

    @Override
    public String booking(AccountItem accountItem) {
        ReturnMsg msg = new ReturnMsg();
        log.info("booking================================START");
        log.info(JSON.toJSONString(accountItem));
        log.info("booking================================END");
        return JSON.toJSONString(msg);
    }
}
