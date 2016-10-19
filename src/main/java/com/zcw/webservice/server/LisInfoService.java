package com.zcw.webservice.server;

        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.zcw.webservice.model.his.*;
        import com.zcw.webservice.model.lis.Bacteria;
        import com.zcw.webservice.model.lis.InspectionItem;
        import com.zcw.webservice.model.lis.PdaSampleInfo;
        import com.zcw.webservice.model.lis.SampleLog;
        import com.zcw.webservice.model.vo.InspectionVo;
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
     * 获取所有住院病人信息
     *
     * @param ward 病区
     * @return 返回Json病人信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getInPatientList")
    String getInPatientList( @QueryParam("ward") String ward);

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
     * 获取医院用户信息列表
     *
     * @return 返回Json医院用户信息[{key:value},{...}]
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Path(value = "/getHospitalUserList")
    String getHospitalUserList();

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
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML})
    @Path(value = "/booking")
    @JsonProperty("accountItem")
    String booking (List<AccountItem> accountItem);


    /**
     * 获取LIS相关检测结果
     * @param barcode
     * @param patientId
     * @return
     */
    String getListTestResult(String barcode,String patientId);

    /**
     *  住院病人检查申请信息查询
     * @param requestType
     * @param executeStatus       住院编号
     * @param ward
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getInPatientRequestInfo")
    String getInPatientRequestInfo(@QueryParam("requestType") @DefaultValue("1") int requestType,
                                   @QueryParam("executeStatus") @DefaultValue("3")int executeStatus,
                                   @QueryParam("ward") String ward,
                                   @QueryParam("bedNo") @DefaultValue("")String bedNo,
                                   @QueryParam("patientId") @DefaultValue("")String patientId);

    /**
     * 门诊检查申请信息查询
     * @param requestType           申请类型
     * @param requestId             申请ID
     * @param testItemId            检验目的ID
     * @param executeStatus         执行状态
     * @param patientType    类型
     * @param patientCode   病人编号
     * @param fromDate      开始时间
     * @param toDate        截止时间
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getOutPatientRequestInfo")
    String getOutPatientRequestInfo(@QueryParam("requestType") @DefaultValue("1") int requestType,
                                    @QueryParam("requestId") @DefaultValue("") String requestId,
                                    @QueryParam("requestDetailId") @DefaultValue("") String requestDetailId,
                                    @QueryParam("testItemId") @DefaultValue("") String testItemId,
                                    @QueryParam("executeStatus") @DefaultValue("0")int executeStatus,
                                    @QueryParam("patientType") @DefaultValue("1")String patientType,
                                    @QueryParam("patientCode")String patientCode,
                                    @QueryParam("fromDate")@DefaultValue("")String fromDate,
                                    @QueryParam("toDate")@DefaultValue("")String toDate);


    /**
     * 体检申请信息查询
     * @param barcode         条码号
     * @param patientId      体检病人编号
     * @param fromDate       开始时间
     * @param toDate        截止时间
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getExaminationRequestInfo")
    String getExaminationRequestInfo(@QueryParam("barcode") @DefaultValue("") String barcode,
                                    @QueryParam("patientId") @DefaultValue("") String patientId,
                                    @QueryParam("fromDate") @DefaultValue("") String fromDate,
                                    @QueryParam("toDate") @DefaultValue("")String toDate);

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
    String returnReport(@QueryParam("reportType")int reportType,
                        @QueryParam("barcode")String barcode,
                        @QueryParam("sampleNo")String sampleNo,
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

    /**
     * 申请状态变更
     * @param param
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML})
    @Path(value = "/requestUpdate")
    String requestUpdate(RequestUpdateParam param);


    /**
     *  LIS将检测结果写入HIS系统
     *
     * @param info 检验结果信息
     * @return 返回Json成功失败信息[{key:value},{...}]
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/saveHisResult")
    String saveHisResult(HisTestInfo info);

    /**
     *  LIS将检测结果写入OldliS系统 用于电子病历
     *
     * @param info 检验结果信息
     * @return 返回Json成功失败信息[{key:value},{...}]
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/saveLisResult")
    String saveLisResult(InspectionVo info);

    /**
     *  LIS将检样本信息写入OldliS系统 用于PDA
     *
     * @param info 样本信息
     * @return 返回Json成功失败信息[{key:value},{...}]
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/savePdaInfo")
    String savePdaInfo(PdaSampleInfo info);

    /**
     * 获取PDA 采集送出时间相关信息
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getPdaInfo")
    String getPdaInfo();


    /**
     * 更新读取状态
     * @param ids
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.TEXT_HTML })
    @Path(value = "/updatePdaStatus")
    String updatePdaStatus(String ids);

    /**
     * 获取PDA 采集送出时间相关信息
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON+ ";charset=UTF-8"})
    @Path(value = "/getTestResult")
    String getTestResult(@QueryParam("patientCode") @DefaultValue("") String patientCode,
                         @QueryParam("patientId") @DefaultValue("") String patientId);

}
