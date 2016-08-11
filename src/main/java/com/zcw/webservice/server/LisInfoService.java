package com.zcw.webservice.server;

import com.zcw.webservice.model.his.AccountItem;
import com.zcw.webservice.model.lis.SampleInfo;
import com.zcw.webservice.model.lis.SampleLog;
import com.zcw.webservice.model.lis.TestResult;
import com.zcw.webservice.model.vo.Report;
import com.zcw.webservice.model.vo.ReturnMsg;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Title: LisInfoService
 * Description:LIS接口
 *
 * @Author:zhou
 * @Date:2016/8/2 1:02
 * @Version:
 */
@WebService
public interface LisInfoService {

    /**
     * 获取检验信息
     *
     * @param barcode 条码号
     * @return 返回Json检验信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getTestInfo/{barcode}")
    String getTestInfo(@PathParam("barcode") String barcode);

    /**
     * 获取细菌信息列表
     *
     * @return 返回Json细菌信息[{key:value},{...}]<BR/>
     * Code编号<BR/>
     * Name名称<BR/>
     * Alias别名<BR/>
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getBacteriaList")
    String getBacteriaList();

    /**
     * 获取检验目的列表
     *
     * @return 返回Json检验目的[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getTestPurposeList")
    String getTestPurposeList();

    /**
     * 获取药敏信息列表
     *
     * @return 返回Json药敏信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getDrugList")
    String getDrugList();

    /**
     * 获取标本种类信息列表
     *
     * @return 返回Json标本种类信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getSampleTypeList")
    String getSampleTypeList();

    /**
     * 获取病人类别信息列表
     *
     * @return 返回Json病人类别[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getPatientTypeList")
    String getPatientTypeList();

    /**
     * 获取病人信息
     *
     * @param patientType 住院类别
     * @param patientCode 住院号/门诊号
     * @return 返回Json病人信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getPatientTypeList/{patientType}{patientCode}")
    String getPatientInfoList(@PathParam("patientType") String patientType, @PathParam("patientCode") String patientCode);

    /**
     * 获取病区信息列表
     *
     * @return 返回Json病区信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getWardList")
    String getWardList();

    /**
     * 获取科室信息列表
     *
     * @return 返回Json科室信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getDepartMentList")
    String getDepartMentList();

    /**
     * 获取样本号
     *
     * @return 返回Json样本号[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getSampleNo/{barcode}")
    String getSampleNo(@PathParam("barcode") String barcode);

    /**
     * Lis已采集未签收
     *
     * @param signStartDate //开始时间
     * @param signEndDate   //结束时间
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getCollectedSampleList/{signStartDate}{signEndDate}")
    String getCollectedSampleList(@PathParam("signStartDate") String signStartDate, @PathParam("signEndDate") String signEndDate);

    /**
     * Lis已签收
     *
     * @param signStartDate //开始时间
     * @param signEndDate   //结束时间
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getReceivedSampleList/{signStartDate}{signEndDate}")
    String getReceivedSampleList(@PathParam("signStartDate") String signStartDate, @PathParam("signEndDate") String signEndDate);

    /**
     * 保存结果
     *
     * @param report 检验结果信息
     * @return 返回Json成功失败信息[{key:value},{...}]
     */
    @POST
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    @Path(value = "/saveTestResult")
    String saveTestResult(Report report);

    /**
     * 标本流转日志记录
     *
     * @param sampleLog 标本日志
     * @return
     */
    @POST
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    @Path(value = "/saveSampleFlowLog")
    String saveSampleFlowLog(SampleLog sampleLog);

    /**
     * 标本退回
     * @param barcode        标本信息
     * @param reason            退回原因
     * @param returnTime        退回时间
     * @param operator          操作人式呈
     * @return
     */
    @POST
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    @Path(value = "/returnSample/{reason}{returnTime}{operator}{barcode}")
    String returnSample(@PathParam("reason") String reason,@PathParam("returnTime")Date returnTime,@PathParam("operator")String operator,@PathParam("barcode")String barcode);

    /**
     *  收费
     * @param accountItem       费用信息
     * @return
     */
    @POST
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    @Path(value = "/booking")
    String booking (AccountItem accountItem);
}
