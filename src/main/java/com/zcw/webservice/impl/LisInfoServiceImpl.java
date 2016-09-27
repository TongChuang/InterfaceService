package com.zcw.webservice.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.zcw.webservice.dao.HisInfoDao;
import com.zcw.webservice.dao.LisInfoDao;
import com.zcw.webservice.model.his.AccountItem;
import com.zcw.webservice.model.his.HisTestInfo;
import com.zcw.webservice.model.his.RequestUpdateParam;
import com.zcw.webservice.model.lis.InspectionItem;
import com.zcw.webservice.model.lis.SampleLog;
import com.zcw.webservice.model.vo.Report;
import com.zcw.webservice.model.vo.ReturnMsg;
import com.zcw.webservice.server.LisInfoService;
import org.apache.log4j.Logger;
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

    @Override
    public String getInPatientList(String ward) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(hisInfoDao.getInPatientList(ward));
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
        //System.out.println(JSON.toJSONString(report));
        ReturnMsg msg = new ReturnMsg();
        try{
            msg = lisInfoDao.saveTestResult(report);
        }catch (Exception e){
            e.printStackTrace();
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        //System.out.println(JSON.toJSONString(msg));
        log.info("saveTestResult================================END");

        return JSON.toJSONString(msg,filter);
    }

    /**
     * 记录标本流转日志
     * @param sampleLog 标本日志
     * @return
     */
    public String saveSampleFlowLog(SampleLog sampleLog) {
        log.info("saveSampleFlowLog================================START");
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
        log.info("saveSampleFlowLog================================END");
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 标本退回
     * @param barcode        条码号
     * @param patientId      病人就诊序号
     * @param returnTime    退回时间
     * @param operator      操作人式呈
     * @param reason        退回原因
     * @return
     */
    public String returnSample(String barcode,String patientId,Date returnTime, String operator, String reason) {
        ReturnMsg msg = new ReturnMsg(0,"退回成功");
        return JSON.toJSONString(msg,filter);
    }

    /**
     * HIS申请状态变更
     * @param param
     * @return
     */
    public String requestUpdate(RequestUpdateParam param) {
        ReturnMsg msg = new ReturnMsg();
        log.info("requestUpdate================================START");
        try{
            msg = hisInfoDao.requestUpdate(param);
        }catch (Exception e){
            e.printStackTrace();
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        log.info("requestUpdate================================END");

        return JSON.toJSONString(msg,filter);
    }

    /**
     * Mlis计费
     * @param accountItem       费用信息
     * @return
     */
    public String booking(List<AccountItem> accountItem) {
        log.info("booking================================START");
        log.info(JSON.toJSONString(accountItem));
        //System.out.println(JSON.toJSONString(accountItem));
        ReturnMsg msg = new ReturnMsg();
        try{
            msg = hisInfoDao.saveBooking(accountItem);
        }catch (Exception e){
            e.printStackTrace();
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        log.info("booking================================END");

        return JSON.toJSONString(msg,filter);
    }

    @Override
    public String getListTestResult(String barcode, String patientId) {
        return null;
    }

    /**
     * 住院病人申请信息获取
     * @param requestType
     * @param executeStatus
     * @param ward
     * @return
     */
    public String  getInPatientRequestInfo(int requestType,int executeStatus,String ward,String bedNo,String patientId) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(hisInfoDao.getInPatientRequestInfo(requestType, executeStatus, ward,bedNo,patientId));
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        log.info(JSON.toJSONString(msg,filter));
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 门诊病人申请信息查询
     * @param requestType
     * @param executeStatus
     *
     * @param patientType
     * @param patientId
     * @param fromDate
     * @param toDate
     * @return
     */
    public String  getOutPatientRequestInfo(int requestType,  String requestId,String requestDetailId,String testItemId,int executeStatus,String patientType, String patientId, String fromDate, String toDate) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg.setState(1);
            msg.setMessage(hisInfoDao.getOutPatientRequestInfo( requestType,   requestId,requestDetailId, testItemId,executeStatus,  patientId,  fromDate,  toDate));
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    @Override
    public String returnReport(int reportType,
                               String barcode,
                               String sampleNo,
                               String operator,
                               Date returnTime,
                               String reason) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = lisInfoDao.returnReport(reportType,barcode,sampleNo,operator,returnTime, reason);
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     * 获取报告状态
     * @param reportType    //报告类型 0 普通 1真菌D内毒素
     * @param barcode
     * @return
     */
    public String getReportStatus(int reportType,String barcode) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int status=lisInfoDao.getReportStatus(reportType,barcode);
            if(status<0){
                msg.setState(0);
                msg.setMessage("未能或取到正确的状态值");
            }else {
                msg.setState(1);
                msg.setMessage(status);
            }
        } catch (Exception e) {
            log.error("获取检验信息异常",e);
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        return JSON.toJSONString(msg,filter);
    }

    /**
     *  LIS将检测结果写入HIS系统
     * @param info    检验结果信息
     * @return
     */
    public String saveHisResult(HisTestInfo info) {
        log.info("saveHisResult================================START");
        ReturnMsg msg = new ReturnMsg();
        try{
            msg = hisInfoDao.saveHisResult(info);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        log.info("saveHisResult================================END");

        return JSON.toJSONString(msg,filter);
    }

    /**
     * LIS结果 用于电子病历结果查询(临时)
     * @param info
     * @return
     * @throws Exception
     */
    @Override
    public String saveLisResult(List<InspectionItem> info) {
        log.info("saveLisResult================================START");
        ReturnMsg msg = new ReturnMsg();
        try{
            msg = lisInfoDao.saveLisResult(info);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            msg.setState(0);
            msg.setMessage(e.getMessage());
        }
        log.info("saveLisResult================================END");
        return JSON.toJSONString(msg,filter);
    }
}
