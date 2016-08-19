package com.zcw.webservice.server;

import com.zcw.webservice.model.his.AccountItem;
import com.zcw.webservice.model.his.PatientRequestInfo;
import com.zcw.webservice.model.lis.Bacteria;
import com.zcw.webservice.model.lis.SampleLog;
import com.zcw.webservice.model.vo.Report;
import com.zcw.webservice.model.vo.ReturnMsg;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Title: LisInfoService
 * Description:LIS接口
 *
 * @Author:zhou
 * @Date:2016/8/2 1:02
 * @Version:
 */
@WebService
@Path("/")
public interface LisInfoService {
    /**
     * 获取检验信息
     *
     * @param barcode 条码号
     * @return 返回Json检验信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
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
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getBacteriaList")
    String  getBacteriaList();

    /**
     * 获取检验目的列表
     *
     * @return 返回Json检验目的[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getTestPurposeList")
    String getTestPurposeList();

    /**
     * 获取药敏信息列表
     *
     * @return 返回Json药敏信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getDrugList")
    String getDrugList();

    /**
     * 获取标本种类信息列表
     *
     * @return 返回Json标本种类信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getSampleTypeList")
    String getSampleTypeList();

    /**
     * 获取病人类别信息列表
     *
     * @return 返回Json病人类别[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
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
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getPatientInfoList")
    String getPatientInfoList(@QueryParam("patientType") String patientType, @QueryParam("patientCode") String patientCode, @QueryParam("patientId")  @DefaultValue("") String patientId);

    /**
     * 获取病区信息列表
     *
     * @return 返回Json病区信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getWardList")
    String getWardList();

    /**
     * 获取科室信息列表
     *
     * @return 返回Json科室信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getDepartMentList")
    String getDepartMentList();

    /**
     * 获取样本号
     *
     * @return 返回Json样本号[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
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
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getCollectedSampleList/{signStartDate}/{signEndDate}")
    String getCollectedSampleList(@PathParam("signStartDate") String signStartDate, @PathParam("signEndDate") String signEndDate);

    /**
     * Lis已签收
     *
     * @param signStartDate //开始时间
     * @param signEndDate   //结束时间
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getReceivedSampleList/{signStartDate}/{signEndDate}")
    String getReceivedSampleList(@PathParam("signStartDate") String signStartDate, @PathParam("signEndDate") String signEndDate);

    /**
     * 保存结果
     *
     * @param report 检验结果信息
     * @return 返回Json成功失败信息[{key:value},{...}]
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/saveTestResult")
    String saveTestResult(Report report);

    /**
     * 标本流转日志记录
     *
     * @param sampleLog 标本日志
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/saveSampleFlowLog")
    String saveSampleFlowLog(SampleLog sampleLog);

    /**
     * 标本退回
     * @param barcode        条码号
     * @param reason        退回原因
     * @param returnTime    退回时间
     * @param operator      操作人式呈
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/returnSample")
    String returnSample(@QueryParam("barcode") String barcode,
                        @QueryParam("patientId") String patientId,
                        @QueryParam("returnTime") Date returnTime,
                        @QueryParam("operator") String operator,
                        @QueryParam("reason")@DefaultValue("")String reason);

    /**
     *  收费
     * @param accountItem       费用信息
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/booking")
    String booking (List<AccountItem> accountItem);

    /**
     * 获取LIS相关检测结果
     * @param barcode
     * @param patientId
     * @return
     */
    String getListTestResult(String barcode,String patientId);

    /**
     *
     * @param patientType
     * @param patientCode       住院、门诊编号
     * @param fromDate
     * @param toDate
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getPatientRequestInfo")
    String getPatientRequestInfo(@QueryParam("requestType") @DefaultValue("1") int requestType,
                                 @QueryParam("executeStatus") @DefaultValue("0")int executeStatus,
                                 @QueryParam("patientType") @DefaultValue("1")String patientType,
                                 @QueryParam("patientCode")String patientCode,
                                 @QueryParam("fromDate")@DefaultValue("")String fromDate,
                                 @QueryParam("toDate")@DefaultValue("")String toDate);

    /**
     * 报告撤回
     * @param barcode        标本信息
     * @param reason            退回原因
     * @param returnTime        退回时间
     * @param operator          操作人式呈
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/returnReport")
    String returnReport(@QueryParam("barcode")String barcode,
                        @QueryParam("operator")String operator,
                        @QueryParam("returnTime")Date returnTime,
                        @QueryParam("reason") @DefaultValue("")String reason);

    /**
     * 标本报告状态
     * @param reportType    //报告类型 0 普通 1真菌D内毒素
     * @param barcode
     * @return
     */
    String getReportStatus(@QueryParam("reportType")int reportType,@QueryParam("barcode")String barcode);
}
