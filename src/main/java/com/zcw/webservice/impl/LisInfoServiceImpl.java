package com.zcw.webservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
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
    /**
     * 用于解决JSON 字段值NULL不显示
     */
    private ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if(v==null)
                return "";
            return v;
        }
    };

//    private static final SerializerFeature[] CONFIG = new SerializerFeature[]{
//        SerializerFeature.WriteNullBooleanAsFalse,//boolean为null时输出false
//        SerializerFeature.WriteMapNullValue, //输出空置的字段
//        SerializerFeature.WriteNonStringKeyAsString,//如果key不为String 则转换为String 比如Map的key为Integer
//        SerializerFeature.WriteNullListAsEmpty,//list为null时输出[]
//        SerializerFeature.WriteNullNumberAsZero,//number为null时输出0
//        SerializerFeature.WriteNullStringAsEmpty//String为null时输出""
//    };
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
            msg.setMessage(lisInfoDao.getTestInfo(barcode));
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
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
            msg.setMessage(lisInfoDao.getBacteriaList());
        } catch (Exception e) {
            log.error("获取细菌信息列表异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        //JSONSerializer.config(SerializerFeature.SortField,false);
        return JSON.toJSONString(msg,filter,SerializerFeature.SortField);
    }

    /**
     * 获取检验目的列表
     * @return
     */
    public String getTestPurposeList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(lisInfoDao.getTestPurposeList());
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 获取药敏信息列表
     * @return
     */
    public String getDrugList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(lisInfoDao.getDrugList());
        } catch (Exception e) {
            log.error("获取药敏信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 获取标本各类信息列表
     * @return
     */
    public String getSampleTypeList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(lisInfoDao.getSampleTypeList());
        } catch (Exception e) {
            log.error("获取标本类型信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 病人类别信息
     * @return
     */
    public String getPatientTypeList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(lisInfoDao.getPatientTypeList());
        } catch (Exception e) {
            log.error("获取病人类型信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 病区信息
     * @return
     */
    public String getWardList() {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(hisInfoDao.getWardList());
        } catch (Exception e) {
            log.error("获取病区信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 科室信息
     * @return
     */
    public String getDepartMentList() {
        ReturnMsg msg = new ReturnMsg();;
        try {
            msg.setState(1);
            msg.setMessage(hisInfoDao.getDepartmentList());
        } catch (Exception e) {
            log.error("获取科室信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
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
            msg.setMessage(hisInfoDao.getDepartmentList());
        } catch (Exception e) {
            log.error("获取样本信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * Lis已采集未签收
     *
     * @param signStartDate //开始时间
     * @param signEndDate   //结束时间
     * @return
     */
    public String getCollectedSampleList(String signStartDate, String signEndDate) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage("");
        } catch (Exception e) {
            log.error("获取信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
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
            msg.setMessage(lisInfoDao.getReceivedSampleList(signStartDate,signEndDate));
        } catch (Exception e) {
            log.error("获取信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 获取病人信息
     * @return
     */
    public String getPatientInfoList(String patientType,String patientCode,String patientId) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(hisInfoDao.getPatientInfo(patientType, patientCode,patientId));
        } catch (Exception e) {
            log.error("获取信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
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
            e.printStackTrace();
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        System.out.println(JSON.toJSONString(msg));
        log.info("saveTestResult================================END");

        return JSON.toJSONString(msg,filter);
    }

    /**
     * 记录标本流转日志
     * @param sampleLog 标本日志
     * @return
     */
    public String saveSampleFlowLog(SampleLog sampleLog) {
        log.info("saveTestResult================================START");
        log.info(JSON.toJSONString(sampleLog));
        //{"OperatorName":"邵晓丽","OperatorNo":"77008","RecordTime":"2016-08-17 19:37:25","Remark":"入库打印条码","SampleNo":"A12008812374","SysName":"微生物系统"}
        ReturnMsg msg = new ReturnMsg();
        try{
            msg = lisInfoDao.saveSampleFlowLog(sampleLog);
        }catch (Exception e){
            e.printStackTrace();
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        log.info("saveTestResult================================END");
        return JSON.toJSONString(msg,filter);
    }


    public String returnSample(String reason, Date returnTime, String operator,String barcode) {
        ReturnMsg msg = new ReturnMsg();
        log.info("returnSample================================START");
        log.info(JSON.toJSONString(reason));
        log.info(JSON.toJSONString(returnTime));
        log.info(JSON.toJSONString(operator));
        log.info(JSON.toJSONString(barcode));
        log.info("returnSample================================END");
        return JSON.toJSONString(msg,filter);
    }

    @Override
    public String booking(AccountItem accountItem) {
        log.info("saveTestResult================================START");
        log.info(JSON.toJSONString(accountItem));
        System.out.println(JSON.toJSONString(accountItem));
        ReturnMsg msg = new ReturnMsg();
        try{
            msg = hisInfoDao.saveBooking(accountItem);
        }catch (Exception e){
            e.printStackTrace();
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        System.out.println(JSON.toJSONString(msg));
        log.info("saveTestResult================================END");

        return JSON.toJSONString(msg,filter);
    }

    @Override
    public String getListTestResult(String barcode, String patientId) {
        return null;
    }

    @Override
    public String  getPatientRequestInfo(String patientType, String patientId, String fromDate, String toDate) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(hisInfoDao.getPatientRequestInfo(patientType, patientId, fromDate, toDate));
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }
}
