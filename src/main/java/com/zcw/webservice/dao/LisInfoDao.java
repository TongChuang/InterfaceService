package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.lis.*;
import com.zcw.webservice.model.vo.*;
import com.zcw.webservice.model.vo.TestResult;
import org.apache.cxf.aegis.type.basic.SqlDateType;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Title:LisInfoDao
 * Description:LIS系统DAO
 *
 * @Author:zhou
 * @Date:2016/8/2 10:06
 * @Version:
 */
@Repository
public class LisInfoDao extends BaseDao {
    private static Logger log = Logger.getLogger(LisInfoDao.class);

    /**
     * 获取检验信息
     *
     * @param barcode //申请条码
     * @return
     */
    public List<TestInfo> getTestInfo(String barcode) throws Exception {
        if(barcode !=null && !barcode.isEmpty()){

            if(barcode.indexOf("A12006")>=0){   //新LIS条码号
                return getNewLisTestInfo(barcode);
            }else {
                return getOldTestInfo(barcode);
            }
        }else {
            return null;
        }

    }
    private List<TestInfo> getOldTestInfo(String barcode) throws Exception {

        //SELECT * FROM t_lis_sampletransPro where ybid ='' and Trans='已计费' 已经计费 状态
        String sql = "select * from vw_testinfo_micro where Barcode =?";
        List<TestInfo> testInfoList = null;
        testInfoList = lisJdbcTemplate.query(sql, new Object[]{barcode},
                new RowMapper<TestInfo>() {
                    public TestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TestInfo testInfo = new TestInfo();
                        testInfo.setBarcode(Util.null2String(rs.getString("Barcode")));
                        testInfo.setPatientTypeCode(Util.null2String(rs.getString("patientTypeCode")));
                        testInfo.setPatientTypeName(Util.null2String(rs.getString("PatientTypeName")));
                        testInfo.setPatientCode(Util.null2String(rs.getString("PatientCode")));
                        testInfo.setPatientName(Util.null2String(rs.getString("PatientName")));
                        testInfo.setPatientSex(Util.null2String(rs.getString("PatientSex")));
                        testInfo.setPatientAge(Util.null2String(rs.getString("PatientAge")));
                        testInfo.setPatientAgeUnit(Util.null2String(rs.getString("PatientAgeUnit")));
                        testInfo.setDepartmentCode(Util.null2String(rs.getString("DepartmentCode")));
                        testInfo.setDepartmentName(Util.null2String(rs.getString("DepartmentName")));
                        testInfo.setInpatientAreaCode(Util.null2String(rs.getString("InpatientAreaCode")));
                        testInfo.setInpatientAreaName(Util.null2String(rs.getString("InpatientAreaName")));
                        testInfo.setBedNo(Util.null2String(rs.getString("BedNo")));
                        testInfo.setDoctorCode(Util.null2String(rs.getString("DoctorCode")));
                        testInfo.setDoctorName(Util.null2String(rs.getString("DoctorName")));
                        testInfo.setSpecimenTypeCode(Util.null2String(rs.getString("SpecimenTypeCode")));
                        testInfo.setSpecimenTypeName(Util.null2String(rs.getString("SpecimenTypeName")));
                        testInfo.setApplyDate(Util.null2String(rs.getString("ApplyDate")));
                        testInfo.setCollectDate(Util.null2String(rs.getString("CollectDate")));
                        testInfo.setCollectAccount(Util.null2String(rs.getString("CollectAccount")));
                        testInfo.setChargeTypeCode(Util.null2String(rs.getString("ChargeTypeCode")));
                        testInfo.setChargeTypeName(Util.null2String(rs.getString("ChargeTypeName")));
                        testInfo.setDiagnosis(Util.null2String(rs.getString("Diagnosis")));
                        //testInfo.setSignDate(Util.null2String(rs.getString("SignDate")));
                        //testInfo.setSignerAccount(Util.null2String(rs.getString("SignerAccount")));
                        String tmpItemCode = Util.null2String(rs.getString("InspectItemCode"));
                        if (!tmpItemCode.equals("")) {
                            if (tmpItemCode.lastIndexOf(",") > 0) {
                                tmpItemCode = tmpItemCode.substring(0, tmpItemCode.lastIndexOf(","));
                            }
                            TestItem testItem = new TestItem();
                            testItem.setId(tmpItemCode);
                            testItem.setCode(tmpItemCode);
                            testItem.setName(Util.null2String(rs.getString("InspectItemName")));
                            testItem.setRequestItemId(Util.null2String(rs.getString("requestItemId")));
                            List<TestItem> testItems = new ArrayList<TestItem>();
                            testItems.add(testItem);
                            testInfo.setTestItems(testItems);

                        }
                        testInfo.setRemark(Util.null2String(rs.getString("Remark")));
                        testInfo.setSampleNo(Util.null2String(rs.getString("SampleNo")));
                        //testInfo.setPatientFileNo(Util.null2String(rs.getString("patientFileNo")));
                        testInfo.setPatientPhone(Util.null2String(rs.getString("patientPhone")));
                        //testInfo.setIsToll(Util.null2String(rs.getString("patientPhone")));
                        testInfo.setPatientId(Util.null2String(rs.getString("patientid")));
                        testInfo.setRequestId(Util.null2String(rs.getString("requestId")));
                        //testInfo.setRequestItemId(Util.null2String(rs.getString("req uestItemId")));
                        return testInfo;
                    }
                });

        if (testInfoList.size() > 0) {
            //获取标本确认时间及确认人
            sql = "select top 1 id, recordtime,operator,ybid,trans from t_lis_sampletransPro where Trans='标本确认'  and ybid=? order by id desc";
            Map result = null;
            try {
                result = (Map) lisJdbcTemplate.queryForMap(sql, new Object[]{testInfoList.get(0).getBarcode()});
                testInfoList.get(0).setSignDate(result.get("recordtime").toString());
                testInfoList.get(0).setSignerAccount((String) result.get("operator"));
            } catch (EmptyResultDataAccessException e) {
                result = null;
            }
            //获取病案号
            try {
                sql = "select top 1 P_wyh from TC_wyhdz where P_DYHM =? order by pid desc";
                result = (Map) lisJdbcTemplate.queryForMap(sql, new Object[]{testInfoList.get(0).getPatientCode()});
                testInfoList.get(0).setPatientFileNo((String) result.get("P_wyh"));
            } catch (EmptyResultDataAccessException e) {
                result = null;
            }
            //获取TESTITEM
//            List<Map<String,String>> testItems = new ArrayList<Map<String,String>>();
//
//            testItems = lisJdbcTemplate.query(sql,new Object[]{barcode},
//                    new RowMapper<Map>() {
//                        public Map<String,String> mapRow(ResultSet rs, int rowNum) throws SQLException {
//                            Map info = new HashMap();
//                            info.put("")
//                        }
//                    })
//
        }
        return testInfoList;
    }



    private List<TestInfo> getNewLisTestInfo(String barcode){
        //SELECT * FROM t_lis_sampletransPro where ybid ='' and Trans='已计费' 已经计费 状态
        String sql = "select * from vw_requestinfo where barcode = ? ";
        List<TestInfo> testInfoList = null;
        testInfoList = newLisJdbcTemplate.query(sql, new Object[]{barcode},
                new RowMapper<TestInfo>() {
                    public TestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TestInfo testInfo = new TestInfo();
                        testInfo.setBarcode(Util.null2String(rs.getString("Barcode")));

                        String patientType = Util.null2String(rs.getString("stayhospitalmode"));
                        String patientName = "";

                        if("1".equals(patientType))
                            patientName="门诊";
                        else if("2".equals(patientType)){
                            patientName="住院";
                        }else {
                            patientName="其他";
                        }
                        testInfo.setPatientTypeCode(patientType);
                        testInfo.setPatientTypeName(patientName);
                        testInfo.setPatientCode(Util.null2String(rs.getString("patientBLH")));
                        testInfo.setPatientName(Util.null2String(rs.getString("PatientName")));
                        testInfo.setPatientSex(Util.null2String(rs.getString("sex")));
                        testInfo.setPatientAge(Util.null2String(rs.getString("age")));
                        testInfo.setPatientAgeUnit(Util.null2String(rs.getString("ageunit")));
                        testInfo.setDepartmentCode(Util.null2String(rs.getString("hossection")));
                        testInfo.setDepartmentName(Util.null2String(rs.getString("hossectionname")));
                        testInfo.setInpatientAreaCode(Util.null2String(rs.getString("wardid")));
                        testInfo.setInpatientAreaName(Util.null2String(rs.getString("wardname")));
                        testInfo.setBedNo(Util.null2String(rs.getString("bed")));
                        testInfo.setDoctorCode(Util.null2String(rs.getString("requester")));
                        testInfo.setDoctorName(Util.null2String(rs.getString("requestername")));
                        testInfo.setSpecimenTypeCode(Util.null2String(rs.getString("specimen")));
                        testInfo.setSpecimenTypeName(Util.null2String(rs.getString("SpecimenTypeName")));
                        testInfo.setApplyDate(Util.null2String(rs.getString("requesttime")));
                        testInfo.setCollectDate(Util.null2String(rs.getString("executetime")));
                        testInfo.setCollectAccount(Util.null2String(rs.getString("executor")));
//                        testInfo.setChargeTypeCode(Util.null2String(rs.getString("ChargeTypeCode")));
//                        testInfo.setChargeTypeName(Util.null2String(rs.getString("ChargeTypeName")));
                        testInfo.setDiagnosis(Util.null2String(rs.getString("diagnostic")));
                        testInfo.setSignDate(Util.null2String(rs.getString("receivetime")));
                        testInfo.setSignerAccount(Util.null2String(rs.getString("receiver")));
                        String tmpItemCode = Util.null2String(rs.getString("YLXH"));
                        String inspectionName = Util.null2String(rs.getString("InspectionName"));
                        List<TestItem> testItems = new ArrayList<TestItem>();
                        if (!tmpItemCode.equals("")) {
                            if (tmpItemCode.lastIndexOf(",") > 0) {
                                tmpItemCode = tmpItemCode.substring(0, tmpItemCode.lastIndexOf(","));
                            }
                            String[] itemCodes = tmpItemCode.split(",");
                            String[] itemNames = inspectionName.split(",");
                            for(int i=0;i<itemCodes.length;i++){
                                TestItem testItem = new TestItem();
                                testItem.setId(itemCodes[i]);
                                testItem.setCode(itemCodes[i]);
                                testItem.setName(itemNames[i]);
                                testItem.setRequestItemId(Util.null2String(rs.getString("laborderorg")));
                                testItems.add(testItem);
                            }
                            testInfo.setTestItems(testItems);
                        }
                        testInfo.setRemark(Util.null2String(rs.getString("Remark")));
                        testInfo.setSampleNo(Util.null2String(rs.getString("SampleNo")));
                        testInfo.setPatientPhone(Util.null2String(rs.getString("phone")));
                        testInfo.setPatientId(Util.null2String(rs.getString("patientid")));
                        testInfo.setRequestId(Util.null2String(rs.getString("requestId")));
                        testInfo.setPatientFileNo(Util.null2String(rs.getString("patientBLH")));
                        return testInfo;
                    }
                });
        return testInfoList;
    }

    /**
     * 获取细菌列表
     *
     * @return
     */
    public List<Bacteria> getBacteriaList() throws Exception {
        List<Bacteria> bacterias = null;
        String sql = "SELECT dh, bh, mc, ywm,td FROM xj_xjzl";
        bacterias = lisJdbcTemplate.query(sql,
                new RowMapper<Bacteria>() {
                    public Bacteria mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Bacteria bacteria = new Bacteria();
                        bacteria.setCode(Util.null2String(rs.getString("dh")));
                        bacteria.setName(Util.null2String(rs.getString("mc")));
                        bacteria.setAlias(Util.null2String(rs.getString("ywm")));
                        bacteria.setChannel(Util.null2String(rs.getString("td")));
                        return bacteria;
                    }
                });
        return bacterias;
    }

    /**
     * 药敏列表信息
     *
     * @return
     */
    public List<Drug> getDrugList() throws Exception {
        String sql = "SELECT dh, bh, mc, ywm FROM xj_ymzl";
        List<Drug> drugs = null;
        drugs = lisJdbcTemplate.query(sql,
                new RowMapper<Drug>() {
                    public Drug mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Drug drug = new Drug();
                        drug.setCode(Util.null2String(rs.getString("dh")));
                        drug.setName(Util.null2String(rs.getString("mc")));
                        drug.setAlias(Util.null2String(rs.getString("ywm")));
                        return drug;
                    }
                });
        return drugs;
    }

    /**
     * 检验目的列表信息
     *
     * @return
     */
    public List<TestPurpose> getTestPurposeList() throws Exception {
        String sql = "SELECT t1.jymddh, t1.jymdmc, t1.jymdsf,t2.dh,t2.mc ,t3.his_id,t3.Chinese_name,t3.Sample_class " +
                "                  FROM xt_jymd t1 " +
                "                  left join xt_bbzl t2 on t1.bbzl = t2.dh " +
                "                  left join Tc_interface_Combine t3  on t1.jymddh = t3.Combine_id " +
                "                  where t1.jyyq =',微生物,'";
        List<TestPurpose> testPurposes = null;
        testPurposes = lisJdbcTemplate.query(sql,
                new RowMapper<TestPurpose>() {
                    public TestPurpose mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TestPurpose testPurpose = new TestPurpose();
                        testPurpose.setCode(Util.null2String(rs.getString("jymddh")));
                        testPurpose.setName(Util.null2String(rs.getString("jymdmc")));
                        testPurpose.setFee(Util.null2String(rs.getString("jymdsf")));
                        testPurpose.setSampleId(Util.null2String(rs.getString("dh")));
                        testPurpose.setSampleName(Util.null2String(rs.getString("mc")));
                        testPurpose.setHisItemCode(Util.null2String(rs.getString("his_id")));
                        testPurpose.setHisItemName(Util.null2String(rs.getString("Chinese_name")));
                        return testPurpose;
                    }
                });
        return testPurposes;
    }

    /**
     * 返回病人类别信息
     *
     * @return List<PatientType>
     * @throws Exception
     */
    public List<PatientType> getPatientTypeList() throws Exception {
        String sql = "select dh,mc from v_xt_sjlb";
        List<PatientType> patientTypes = null;
        patientTypes = lisJdbcTemplate.query(sql,
                new RowMapper<PatientType>() {
                    public PatientType mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientType patientType = new PatientType();
                        patientType.setCode(Util.null2String(rs.getString("jymddh")));
                        patientType.setName(Util.null2String(rs.getString("jymdmc")));
                        return patientType;
                    }
                });
        return patientTypes;
    }

    /**
     * 标本类型列表信息
     *
     * @return
     */
    public List<SampleType> getSampleTypeList() throws Exception {
        String sql = "SELECT dh,mc FROM xt_bbzl";
        List<SampleType> sampleTypes = null;
        sampleTypes = lisJdbcTemplate.query(sql,
                new RowMapper<SampleType>() {
                    public SampleType mapRow(ResultSet rs, int rowNum) throws SQLException {
                        SampleType sampleType = new SampleType();
                        sampleType.setCode(Util.null2String(rs.getString("dh")));
                        sampleType.setName(Util.null2String(rs.getString("mc")));
                        return sampleType;
                    }
                });
        return sampleTypes;
    }

    /**
     * LIS已签收标本信息
     *
     * @param signStartDate
     * @param signEndDate
     * @return
     */
    public List<TestInfo> getReceivedSampleList(String signStartDate, String signEndDate) throws Exception {
        List<TestInfo> testInfoList = null;
        String sql = "select  * from vw_testinfo_micro where status='1' and  CollectDate>=? and CollectDate<=?";
        testInfoList = lisJdbcTemplate.query(sql, new Object[]{signStartDate, signEndDate},
                new RowMapper<TestInfo>() {
                    public TestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TestInfo testInfo = new TestInfo();
                        testInfo.setBarcode(Util.null2String(rs.getString("Barcode")));
                        testInfo.setPatientTypeCode(Util.null2String(rs.getString("patientTypeCode")));
                        testInfo.setPatientTypeName(Util.null2String(rs.getString("PatientTypeName")));
                        testInfo.setPatientCode(Util.null2String(rs.getString("PatientCode")));
                        testInfo.setPatientName(Util.null2String(rs.getString("PatientName")));
                        testInfo.setPatientSex(Util.null2String(rs.getString("PatientSex")));
                        testInfo.setPatientAge(Util.null2String(rs.getString("PatientAge")));
                        testInfo.setPatientAgeUnit(Util.null2String(rs.getString("PatientAgeUnit")));
                        testInfo.setDepartmentCode(Util.null2String(rs.getString("DepartmentCode")));
                        testInfo.setDepartmentName(Util.null2String(rs.getString("DepartmentName")));
                        testInfo.setInpatientAreaCode(Util.null2String(rs.getString("InpatientAreaCode")));
                        testInfo.setInpatientAreaName(Util.null2String(rs.getString("InpatientAreaName")));
                        testInfo.setBedNo(Util.null2String(rs.getString("BedNo")));
                        testInfo.setDoctorCode(Util.null2String(rs.getString("DoctorCode")));
                        testInfo.setDoctorName(Util.null2String(rs.getString("DoctorName")));
                        testInfo.setSpecimenTypeCode(Util.null2String(rs.getString("SpecimenTypeCode")));
                        testInfo.setSpecimenTypeName(Util.null2String(rs.getString("SpecimenTypeName")));
                        testInfo.setApplyDate(Util.null2String(rs.getString("ApplyDate")));
                        testInfo.setCollectDate(Util.null2String(rs.getString("CollectDate")));
                        testInfo.setCollectAccount(Util.null2String(rs.getString("CollectAccount")));
                        testInfo.setChargeTypeCode(Util.null2String(rs.getString("ChargeTypeCode")));
                        testInfo.setChargeTypeName(Util.null2String(rs.getString("ChargeTypeName")));
                        testInfo.setDiagnosis(Util.null2String(rs.getString("Diagnosis")));
                        testInfo.setSignDate(Util.null2String(rs.getString("SignDate")));
                        testInfo.setSignerAccount(Util.null2String(rs.getString("SignerAccount")));
                        String tmpItemCode = Util.null2String(rs.getString("InspectItemCode"));
                        tmpItemCode = tmpItemCode.substring(0, tmpItemCode.lastIndexOf(","));
                        TestItem testItem = new TestItem();
                        testItem.setId(tmpItemCode);
                        testItem.setCode(tmpItemCode);
                        testItem.setName(Util.null2String(rs.getString("InspectItemName")));
                        List<TestItem> testItems = new ArrayList<TestItem>();
                        testItems.add(testItem);
                        testInfo.setTestItems(testItems);
                        testInfo.setRemark(Util.null2String(rs.getString("Remark")));
                        testInfo.setSampleNo(Util.null2String(rs.getString("SampleNo")));
                        return testInfo;
                    }
                });
        return testInfoList;

    }


    /**
     * 保存检测结果至LIS系统
     *
     * @param info
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg saveTestResult(Report info) throws Exception {
        ReturnMsg msg = new ReturnMsg();
        String patientId = Util.null2String(info.getSampleInfo().getPatientId());           //就诊卡号
        String patientName = Util.null2String(info.getSampleInfo().getPatientName());       //病人姓名
        String partientCode = Util.null2String(info.getSampleInfo().getPatientCode());        //住院号、门诊号
        String barcode = Util.null2String(info.getSampleInfo().getBarcode());               //条码号
        String sampleType = Util.null2String(info.getSampleInfo().getSampleTypeCode());        //样本类型
        String sexValue = Util.null2String(info.getSampleInfo().getSex());                      //性别
        int sex = (info.getSampleInfo().getSex().equals("女")) ? 2 : 1;

        String sql = "";
        log.info(JSON.toJSONString(info));

        if (info == null) {
            return new ReturnMsg(0, "参数不能为空！");
        }
        //判断病人是否相同
        sql = "select count(0) as cnt from f_k_ybxx " +
                "where ybid =? and brxm=? and brxb =? and bbzl=? and brbq=?";
        log.info(sql);
        Long count = lisJdbcTemplate.queryForObject(sql, new Object[]{barcode, patientName, sex, sampleType, partientCode}, Long.class);
        if (count <= 0) {
            log.info("没有记录或病人信息不一致！");
            return new ReturnMsg(0, "没有记录或病人信息不一致！");
        }

        if(info.getSampleInfo().getBarcode().indexOf("A12006")>=0){
            //保存信息至新LIS系统
            msg = saveNewLisTestResult(info);
        }else {
            if (info.getReportType() == 0) {
                //普通培养报告
                msg = saveTestResult1(info);
            } else {
                //真菌D、内毒素报告
                msg = saveTestResult2(info);
            }
        }
        return msg;
    }

    /**
     * 保存细菌普通检测结果
     * A12000171874
     *
     * @param report
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    private ReturnMsg saveTestResult1(Report report) throws Exception {
        final SampleInfo sampleInfo = report.getSampleInfo();
        final int sex = (sampleInfo.getSex().equals("女")) ? 2 : 1;

        //获取医院(客户名称)
        String custCode = sampleInfo.getBarcode().length() > 6 ? sampleInfo.getBarcode().substring(0, 6) : "";
        final String custName = lisJdbcTemplate.queryForObject("select mc from xt_yymc_print where dh=?", new Object[]{custCode}, String.class);

        String sql = "insert into xj_ybxx(byh,ybbh,cdrq,brxm,brxb," +
                "brnl,nllx,brch,bbzl,cyrq,lczd,sjys,jyys,shys,jymd" +
                ",jymdmc,bgrq,brbq,khks,brphone,papersize,bingrenlb,ybzt,khmc,brkb,cjtime) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, Util.null2String(sampleInfo.getBarcode()));           //条码号
                ps.setString(2, Util.null2String(sampleInfo.getSampleId()));          //样本ID
                ps.setTimestamp(3, new java.sql.Timestamp(sampleInfo.getTestDateTime().getTime()));      //测定日期
                ps.setString(4, sampleInfo.getPatientName());       //病人姓名
                ps.setInt(5, sex);                                  //病人性别
                ps.setString(6, Util.null2String(sampleInfo.getAge()));               //年龄
                ps.setString(7, Util.null2String(sampleInfo.getAgeType()));           //年龄类型
                ps.setString(8, Util.null2String(sampleInfo.getBedNo()));             //病人床号
                ps.setString(9, Util.null2String((sampleInfo.getSampleType())));        //标本类型
                ps.setTimestamp(10, new java.sql.Timestamp(sampleInfo.getSamplingTime().getTime()));      //接收时间
                ps.setString(11, Util.null2String(sampleInfo.getClinicalDiagnosis())); //临床诊断
                ps.setString(12, Util.null2String(sampleInfo.getInspectDoctor()));     //送检医生
                ps.setString(13, Util.null2String(sampleInfo.getTestDoctor()));        //检验医生
                ps.setString(14, Util.null2String(sampleInfo.getAuditDoctor()));       //审核医生
                ps.setString(15, Util.null2String(sampleInfo.getTestDestinationNo()));   //检验目的编号
                ps.setString(16, Util.null2String(sampleInfo.getTestDestinationName()));  //检验目的
                ps.setTimestamp(17, new java.sql.Timestamp(sampleInfo.getReportDateTime().getTime()));       //报告日期
                ps.setString(18, Util.null2String(sampleInfo.getPatientCode()));       //住院号
                ps.setString(19, Util.null2String(sampleInfo.getBillDepartment()));    //开单科室
                ps.setString(20, Util.null2String(sampleInfo.getPatientPhone()));      //病人电话
                ps.setString(21, "A5");                              //纸张大小
                ps.setString(22, Util.null2String(sampleInfo.getPatientCode()));      //病人类别编号
                ps.setString(23, "d");                              //样本状态(初审)
                ps.setString(24, Util.null2String(custName));                         //客户名称(医院名称)
                ps.setString(25, "外观正常");                       //标本外观
                ps.setTimestamp(26, new java.sql.Timestamp(sampleInfo.getSamplingTime().getTime())); //采样时间
            }
        });

        //普通报告
        final List<TestResult> results = report.getResults();
        sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg,jg1) values(?,?,?,?,?)";
        this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                for (TestResult testResult : results) {
                    ps.setString(1, sampleInfo.getSampleId());              //样本号
                    ps.setString(2, testResult.getResultType());            //结果类型
                    ps.setInt(3, testResult.getResultTypeId());             //结果类型序号
                    ps.setString(4, testResult.getResult());                //结果
                    ps.setString(5, testResult.getCount());                 //菌量计数
                    // ps.setString(5, results.get(i).getDrugResistance());  //耐药标志
                    ps.addBatch();
                }
                Object o = ps.executeBatch();
                return o;
            }
        });
        //异常测试
        /*if (1 == 1) {
            throw new Exception("错误！！！");
        }*/
        //更新药敏信息
        final List<DrugResult> drugResults = report.getDrugResults();
        if (drugResults != null && drugResults.size() > 0) {
            sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg,jg1,jg2,kbvalue,jgxh) values(?,?,?,?,?,?,?,?)";
            this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    boolean flag = false;
                    flag = (drugResults.size() > 0 && Util.getIntValue(drugResults.get(0).getResultCode()) > 1);
                    for (DrugResult drugResult : drugResults) {
                        ps.setString(1, sampleInfo.getSampleId());                          //样本号
                        ps.setString(2, "ym");                                              //结果类型
                        ps.setInt(3, Util.getIntValue(drugResult.getResultCode()));         //结果类型序号
                        ps.setString(4, drugResult.getName());                              //结果(抗生素名称)
                        ps.setString(5, drugResult.getAbnormalResult());                    //异菌范围
                        ps.setString(6, drugResult.getResultValue());                       //结果值(R/S/I)
                        ps.setString(7, drugResult.getReference());                         //KB参考范围
                        ps.setString(8, drugResult.getOrderNo());                           //药敏顺序
                        ps.addBatch();
                    }
                    Object o = ps.executeBatch();
                    return o;
                }
            });
        }
        return new ReturnMsg(1, "保存成功");
    }

    /**
     * 保存真菌D、内毒素检测结果
     *
     * @param report
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    private ReturnMsg saveTestResult2(Report report) throws Exception {
        String sql = "insert into lis_ybxx(yqdh,ybid,ybbh,byh,cdrq,brxm,brxb," +
                "brnl,nllx,brkb,brch,bbzl,cyrq,lczd,sjys,jyys,shys,jymd" +
                ",jymdmc,bgrq,brbq,khks,brphone,papersize,bingrenlb,ybzt,cjtime) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        final SampleInfo sampleInfo = report.getSampleInfo();
        final int sex = (sampleInfo.getSex().equals("女")) ? 2 : 1;

        //获取医院(客户名称)
        String custCode = sampleInfo.getBarcode().length() > 6 ? sampleInfo.getBarcode().substring(0, 6) : "";
        final String custName = lisJdbcTemplate.queryForObject("select mc from xt_yymc_print where dh=?", new Object[]{custCode}, String.class);
        //保存样本信息
        lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, "微生物");                                                     //仪器代号
                ps.setString(2, sampleInfo.getBarcode());                                       //条码号
                ps.setString(3, sampleInfo.getSampleId());                                       //样本ID
                ps.setString(4, Util.null2String(custName));                         //客户名称(医院名称)
                ps.setTimestamp(5, new java.sql.Timestamp(sampleInfo.getTestDateTime().getTime()));      //测定日期
                ps.setString(6, sampleInfo.getPatientName());                                   //病人姓名
                ps.setInt(7, sex);                                                              //病人性别
                ps.setObject(8, sampleInfo.getAge());                                           //年龄
                ps.setObject(9, sampleInfo.getAgeType());                                       //年龄类型
                ps.setString(10, "外观正常");                                                   //标本性状
                ps.setObject(11, sampleInfo.getBedNo());                                         //病人床号
                ps.setObject(12, sampleInfo.getSampleTypeCode());                               //标本类型
                ps.setTimestamp(13, new java.sql.Timestamp(sampleInfo.getSamplingTime().getTime()));      //接收时间
                ps.setObject(14, sampleInfo.getClinicalDiagnosis());                            //临床诊断
                ps.setObject(15, sampleInfo.getInspectDoctor());                                //送检医生
                ps.setObject(16, sampleInfo.getTestDoctor());                                   //检验医生
                ps.setObject(17, sampleInfo.getAuditDoctor());                                  //审核医生
                ps.setObject(18, sampleInfo.getTestDestinationNo());                            //检验目的编号
                ps.setObject(19, sampleInfo.getTestDestinationName());                          //检验目的
                ps.setTimestamp(20, new java.sql.Timestamp(sampleInfo.getReportDateTime().getTime()));    //报告日期
                ps.setObject(21, sampleInfo.getPatientCode());                                  //病人档案号
                ps.setObject(22, sampleInfo.getBillDepartment());                               //开单科室
                ps.setObject(23, sampleInfo.getPatientPhone());                                 //病人电话
                //ps.setObject(22, sampleInfo.getCreateTime());                                 //创建日期
                ps.setObject(24, "A5");
                ps.setString(25, Util.null2String(sampleInfo.getPatientCode()));      //病人类别编号
                ps.setString(26, "d");                              //样本状态(初审)
                //ps.setString(27, "外观正常");                       //标本外观
                ps.setTimestamp(27, new java.sql.Timestamp(sampleInfo.getSamplingTime().getTime())); //采样时间                              //样本状态(初审)
            }
        });

        //真菌D内毒素报告
        sql = "insert into lis_xmcdz(yqdh,cdrq,ybbh,xmdh,xmbh,xmcdz,gdbj,ckz,dw) values(?,?,?,?,?,?,?,?,?)";
        final List<TestResult> results = report.getResults();
        this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                for (TestResult testResult : results) {
                    ps.setString(1, "微生物");                                             //仪器代号
                    ps.setTimestamp(2, new java.sql.Timestamp(sampleInfo.getReportDateTime().getTime())); //测定日期
                    ps.setString(3, sampleInfo.getSampleId());                              //样本编号
                    ps.setString(4, testResult.getTestItemCode());                          //结果项目编号
                    ps.setString(5, testResult.getTestItemOrder());                         //结果类型序号
                    ps.setString(6, testResult.getResult());                                //结果
                    ps.setString(7, testResult.getAbnormalFlag());                          //异常标志
                    ps.setString(8, testResult.getReference());                             //参考值
                    ps.setString(9, testResult.getUnit());                                  //单位
                    ps.addBatch();
                }
                Object o = ps.executeBatch();
                //ps.getConnection().commit();
                return o;
            }
        });
        return new ReturnMsg(1, "保存成功");
    }


    /**
     * 保存微生物结果至新Lis系统
     * @param report
     * @return
     */
    private ReturnMsg saveNewLisTestResult(Report report){
        final SampleInfo sampleInfo = report.getSampleInfo();

        //普通报告
        final List<TestResult> results = report.getResults();
        String sql = "insert into l_testresult(sampleno,testid,measuretime,operator,refhi," +
                "reflo,resultflag,sampletype,teststatus,unit,testname,hint) " +
                " values(?,?,?,?,?,?,?,?,?,?,?,?)";
        this.newLisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                int i=0;
                for (TestResult testResult : results) {
                    i++;
                    ps.setString(1, sampleInfo.getSampleId());              //样本号
                    ps.setString(2, testResult.getTestItemCode());                  //结果类型
                    ps.setTimestamp(3, new java.sql.Timestamp(sampleInfo.getTestDateTime().getTime()));      //测定日期
                    ps.setString(4, Util.null2String(sampleInfo.getTestDoctor()));        //检验医生
                    String []  reference =   Util.getRefernce(testResult.getReference());
                    ps.setString(5, reference[0]);                                          //结果
                    ps.setString(6, reference[1]);                                          //菌量计数
                    ps.setString(7,"B"+String.format("%03d",i));
                    ps.setString(8,sampleInfo.getSampleType());
                    ps.setInt(9,5);
                    ps.setString(10,testResult.getUnit());
                    ps.setString(11,testResult.getResult());
                    ps.setString(12,testResult.getCount());
                    // ps.setString(5, results.get(i).getDrugResistance());  //耐药标志
                    ps.addBatch();
                }
                Object o = ps.executeBatch();
                return o;
            }
        });

        //更新药敏信息
        final List<DrugResult> drugResults = report.getDrugResults();
        if (drugResults != null && drugResults.size() > 0) {
            sql = "insert into l_testresult(sampleno,testid,measuretime,operator,refhi," +
                    "reflo,resultflag,sampletype,testresult,teststatus,unit,testname,hint) " +
                    " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            this.newLisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    int i = 0;
                    for (DrugResult drugResult : drugResults) {
                        i++;
                        ps.setString(1, sampleInfo.getSampleId());              //样本号
                        ps.setString(2, drugResult.getCode());                  //结果类型
                        ps.setTimestamp(3, new java.sql.Timestamp(sampleInfo.getTestDateTime().getTime()));      //测定日期
                        ps.setString(4, Util.null2String(sampleInfo.getTestDoctor()));        //检验医生
                        String []  reference =   Util.getRefernce(drugResult.getReference());
                        ps.setString(5, reference[0]);                                          //结果
                        ps.setString(6, reference[1]);                                          //菌量计数
                        ps.setString(7,"A"+String.format("%03d",i));
                        ps.setString(8,sampleInfo.getSampleType());
                        ps.setString(8,drugResult.getAbnormalResult());
                        ps.setInt(9,5);
                        ps.setString(10,drugResult.getUnit());
                        ps.setString(11,drugResult.getName());
                        ps.setString(12,drugResult.getResultValue());
                        // ps.setString(5, results.get(i).getDrugResistance());  //耐药标志
                        ps.addBatch();
                    }
                    Object o = ps.executeBatch();
                    return o;
                }
            });
        }
        return new ReturnMsg(1, "保存成功");
    }

    /**
     * 日志记录
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg saveSampleFlowLog(final SampleLog sampleLog) throws Exception {
        if (sampleLog == null)
            return new ReturnMsg(0, "参数不能为空");
        if (sampleLog.getSampleNo() == null || sampleLog.getSampleNo().equals(""))
            return new ReturnMsg(0, "样本号不能为空");
        String sql = "insert into t_lis_sampletransPro(ybid,recordtime,operator,trans)values(?,?,?,?) ";
        lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, Util.null2String(sampleLog.getSampleNo()));                 //样本号
                ps.setTimestamp(2, new java.sql.Timestamp(sampleLog.getRecordTime().getTime()));      //操作日期
                ps.setString(3, Util.null2String(sampleLog.getOperatorName()));             //操作人
                ps.setString(4, Util.null2String(sampleLog.getRemark()));                   //内容
            }
        });
        return new ReturnMsg(1, "保存成功");
    }

    /**
     * @param reason
     * @param returnTime
     * @param operator
     * @param barcode
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg returnReport(int reportType,
                                  String barcode,
                                  String sampleNo,
                                  String operator,
                                  Date returnTime,
                                  String reason) throws Exception {
        if (barcode == null || barcode.equals("")) {
            return new ReturnMsg(0, "条码号不能为空");
        }
        if (sampleNo == null || sampleNo.equals("")) {
            return new ReturnMsg(0, "样本号不能为空");
        }
        String sql = "";
        if (reportType == 0) {
            sql = "select ybzt from xj_ybxx where byh = ? and ybbh=?";
        } else if (reportType == 1) {
            sql = "select ybzt from lis_ybxx where ybid = ? and ybbh = ?";
        }
        String status = lisJdbcTemplate.queryForObject(sql, new Object[]{barcode, sampleNo}, String.class);
        if (status != null && !status.isEmpty()) {
            if (status.equals("p") || status.equals("s")) {
                return new ReturnMsg(0, "样本已审核或打印，不允许删除");
            }
        }
        //检测报告状态
        if (status.equals("d")) {
            //删除报告
            if (reportType == 0) {
                sql = "delete from xj_xmcdz where ybbh=?";
                lisJdbcTemplate.update(sql, new Object[]{sampleNo});
                sql = "delete from xj_ybxx where ybbh=? and byh=?";
                lisJdbcTemplate.update(sql, new Object[]{sampleNo, barcode});
            } else if (reportType == 1) {
                sql = "delete from lis_xmcdz where ybbh=?";
                lisJdbcTemplate.update(sql, new Object[]{sampleNo});
                sql = "delete from lis_ybxx where ybid=? and ybbh=?";
                lisJdbcTemplate.update(sql, new Object[]{sampleNo, barcode});
            }
        }
        return new ReturnMsg(1, "删除成功");
    }

    /**
     * 获取报告状态
     *
     * @param reportType
     * @param barcode
     * @return
     */
    public int getReportStatus(int reportType, String barcode) throws Exception {
        String sql = "";
        int returnValue = -1;   //0 未审 1 初审 2已审 3已打 -1 其他
        if (reportType == 0) {
            sql = "select ybzt from xj_ybxx where byh = ?";
        } else if (reportType == 1) {
            sql = "select ybzt from lis_ybxx where ybid = ?";
        }
        String status = "";
        try {
            status = lisJdbcTemplate.queryForObject(sql, new Object[]{barcode}, String.class);
            if (status.equals("")) {
                returnValue = 0;
            } else if (status.equals("d")) {
                returnValue = 1;
            } else if (status.equals("s")) {
                returnValue = 2;
            } else if (status.equals("p")) {
                returnValue = 3;
            }
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            returnValue = -1;
        }
        return returnValue;
    }

    /**
     * LIS结果 用于电子病历结果查询(临时)
     *
     * @param info
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg saveLisResult(final InspectionVo info) throws Exception {
        if (info == null)
            return new ReturnMsg(0, "参数不能为空");

        //删除原有样本信息
        String sql = "delete from EHR_inspection_Info where ybid=?";
        lisJdbcTemplate.update(sql, new Object[]{info.getInspectionInfo().getBarcode()});

        //保存样本信息
        sql = "insert into EHR_inspection_Info(id,inspection_Id,brxh,patientid,brxm,brxb,brnl,dept_name,ward_name," +
                "brch,bbzl,sjys,cdrq,his_id,operator,recordtime,report_name,shrq,shys,bgrq,jymd," +
                "ybid,filedir,filedir1,filestate,patienttype,samplestatus)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, Util.null2String(info.getInspectionInfo().getInspectionId()));
                ps.setString(2, Util.null2String(info.getInspectionInfo().getInspectionId()));
                ps.setString(3, Util.null2String(info.getInspectionInfo().getPatientNo()));
                ps.setString(4, Util.null2String(info.getInspectionInfo().getPatientId()));
                ps.setString(5, Util.null2String(info.getInspectionInfo().getPatientName()));
                ps.setString(6, Util.null2String(info.getInspectionInfo().getSex()));
                ps.setString(7, Util.null2String(info.getInspectionInfo().getAge()));
                ps.setString(8, Util.null2String(info.getInspectionInfo().getDepartment()));
                ps.setString(9, Util.null2String(info.getInspectionInfo().getWardName()));
                ps.setString(10, Util.null2String(info.getInspectionInfo().getBedNo()));
                ps.setString(11, Util.null2String(info.getInspectionInfo().getSampleType()));
                ps.setString(12, Util.null2String(info.getInspectionInfo().getRequestName()));
                if(info.getInspectionInfo().getTestTime()==null){
                    ps.setNull(13, Types.DATE);
                }else{
                    ps.setTimestamp(13,new java.sql.Timestamp(info.getInspectionInfo().getTestTime().getTime()));
                }
                ps.setString(14, Util.null2String(info.getInspectionInfo().getTesterHisId()));
                ps.setString(15, Util.null2String(info.getInspectionInfo().getTesterName()));
                if(info.getInspectionInfo().getRequestTime()==null){
                    ps.setNull(16, Types.DATE);
                }else{
                    ps.setTimestamp(16,new java.sql.Timestamp(info.getInspectionInfo().getRequestTime().getTime()));
                }
                ps.setString(17, Util.null2String(info.getInspectionInfo().getReportName()));
                if(info.getInspectionInfo().getAuditTime()==null){
                    ps.setNull(18, Types.DATE);
                }else{
                    ps.setTimestamp(18,new java.sql.Timestamp(info.getInspectionInfo().getAuditTime().getTime()));
                }
                ps.setString(19, Util.null2String(info.getInspectionInfo().getAuditName()));
                if(info.getInspectionInfo().getAuditTime()==null){
                    ps.setNull(20, Types.DATE);
                }else{
                    ps.setTimestamp(20,new java.sql.Timestamp(info.getInspectionInfo().getReportTime().getTime()));
                }
                ps.setString(21, Util.null2String(info.getInspectionInfo().getTestName()));
                ps.setString(22, Util.null2String(info.getInspectionInfo().getBarcode()));
                ps.setString(23, "E:\\GenerateReport\\ReportTemp\\" + info.getInspectionInfo().getBarcode() + ".pdf");
                ps.setString(24, "\\\\10.31.96.39\\GenerateReport\\ReportTemp\\" + info.getInspectionInfo().getBarcode() + ".pdf");
                ps.setString(25, "可打");
                ps.setString(26, info.getInspectionInfo().getPatientType());
                ps.setString(27, info.getInspectionInfo().getSampleStatus());

            }
        });


        //删除样本信息
        sql = "delete from EHR_inspection_item where barcode=?";
        lisJdbcTemplate.update(sql, new Object[]{info.getInspectionInfo().getBarcode()});

        //插入结果信息
        sql = "insert into EHR_inspection_item(inspectionId,testItemId," +
                "testItemName_EN,testItemName_CN,unit,orderNum,reference,resultFlag,barcode,testresult) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                for (InspectionItem inspectionItem : info.getInspectionItemList()) {
                    ps.setString(1, inspectionItem.getInspectionId());
                    ps.setString(2, inspectionItem.getTestItemId());
                    ps.setString(3, inspectionItem.getTestItemName_EN());
                    ps.setString(4, inspectionItem.getGetTestItemName_CN());
                    ps.setString(5, inspectionItem.getUnit());
                    ps.setString(6, inspectionItem.getOrderNum());
                    ps.setString(7, inspectionItem.getReference());
                    ps.setString(8, inspectionItem.getResultFlag());
                    ps.setString(9, inspectionItem.getBarcode());
                    ps.setString(10, inspectionItem.getTestResult());
                    ps.addBatch();
                }
                Object o = ps.executeBatch();
                return o;
            }
        });
        return new ReturnMsg(1, "保存成功");
    }

    /**
     * LIS结果 用于电子病历结果查询(临时)
     *
     * @param info
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg getSampllingTime(final List<InspectionItem> info) throws Exception {
        if (info == null || info.size() == 0)
            return new ReturnMsg(0, "参数不能为空");

        //删除样本信息
        String sql = "delete from EHR_inspection_item where barcode=?";
        lisJdbcTemplate.update(sql, new Object[]{info.get(0).getBarcode()});

        //插入结果信息
        sql = "insert into EHR_inspection_item(inspectionId,testItemId," +
                "testItemName_EN,testItemName_CN,unit,orderNum,reference,resultFlag,barcode,testresult) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                for (InspectionItem inspectionItem : info) {
                    ps.setString(1, inspectionItem.getInspectionId());
                    ps.setString(2, inspectionItem.getTestItemId());
                    ps.setString(3, inspectionItem.getTestItemName_EN());
                    ps.setString(4, inspectionItem.getGetTestItemName_CN());
                    ps.setString(5, inspectionItem.getUnit());
                    ps.setString(6, inspectionItem.getOrderNum());
                    ps.setString(7, inspectionItem.getReference());
                    ps.setString(8, inspectionItem.getResultFlag());
                    ps.setString(9, inspectionItem.getBarcode());
                    ps.setString(10, inspectionItem.getTestResult());
                    ps.addBatch();
                }
                Object o = ps.executeBatch();
                return o;
            }
        });
        return new ReturnMsg(1, "保存成功");
    }


    /**
     * 保存结果至LIS PDA信息，用于PDA标本采集
     *
     * @param info
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg savePdaInfo(final PdaSampleInfo info) throws Exception {
        if (info == null)
            return new ReturnMsg(0, "参数不能为空");

        //删除样本信息
        String sql = "select count(1) cnt from T_TAT_EWELL where REPORT_ID=? AND PATIENT_ID=?";
        int cnt = lisJdbcTemplate.queryForObject(sql, new Object[]{info.getBarcode(), info.getPatientId()}, Integer.class);
        if (cnt > 0) {
            sql = "UPDATE T_TAT_EWELL SET REPORT_TIME=?,REPORT_NAME=?,REQUEST_TIME=?,REQUEST_NAME=?,RECERIVE_TIME=?,RECERIVE_NAME=?" +
                    " WHERE REPORT_ID=? AND PATIENT_ID=?";
            this.lisJdbcTemplate.update(sql,
                    new PreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            ps.setTimestamp(1, new java.sql.Timestamp(info.getReportTime().getTime()));
                            ps.setString(2, Util.null2String(info.getReportName()));
                            ps.setTimestamp(3, new java.sql.Timestamp(info.getRequestTime().getTime()));
                            ps.setString(4, Util.null2String(info.getRequestName()));
                            ps.setTimestamp(5, new java.sql.Timestamp(info.getReceiveTime().getTime()));
                            ps.setString(6, Util.null2String(info.getReceiveName()));
                            ps.setString(7, Util.null2String(info.getBarcode()));
                            ps.setString(8, Util.null2String(info.getPatientId()));
                        }
                    });
        } else {
            //插入结果信息
            sql = "insert into T_TAT_EWELL(PATIENT_ID,PATIENT_NUMBER," +
                    "REPORT_ID,REPORT_ITEMID,REPORT_ITEMNAME,REQUEST_TIME,REQUEST_NAME,BQDM,BQMC) " +
                    "values(?,?,?,?,?,?,?,?,?)";

            this.lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, Util.null2String(info.getPatientId()));
                    ps.setString(2, Util.null2String(info.getPatientNo()));
                    ps.setString(3, Util.null2String(info.getBarcode()));
                    ps.setString(4, Util.null2String(info.getItemId()));
                    ps.setString(5, Util.null2String(info.getItemName()));
                    ps.setTimestamp(6, new java.sql.Timestamp(info.getRequestTime().getTime()));
                    ps.setString(7, Util.null2String(info.getRequestName()));
                    ps.setString(8, Util.null2String(info.getWardId()));
                    ps.setString(9, Util.null2String(info.getWardName()));
                }
            });
        }

        return new ReturnMsg(1, "保存成功");
    }

    /**
     * 获取PDA 采集时间及送出时间等信息、用于更新LIS相关时间信息
     *
     * @return
     */
    public List<PdaSampleInfo> getPdaInfo() {
        List<PdaSampleInfo> pdaSampleInfoList = new ArrayList<PdaSampleInfo>();
        String sql = "select inspectionid,execute_time,execute_name,send_time,send_name " +
                " from T_Sample_Trans_Time where (flag is null or flag=0) " +
                " and InspectionId like 'A12006%' and Send_Time is not null ";
        pdaSampleInfoList = lisJdbcTemplate.query(sql,
                new RowMapper<PdaSampleInfo>() {
                    public PdaSampleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PdaSampleInfo pdaSampleInfo = new PdaSampleInfo();
                        pdaSampleInfo.setBarcode(Util.null2String(rs.getString("inspectionid")));
                        pdaSampleInfo.setExecuteTime(rs.getTimestamp("execute_time"));
                        pdaSampleInfo.setExecuteName(Util.null2String(rs.getString("execute_name")));
                        pdaSampleInfo.setSendTime(rs.getTimestamp("send_time"));
                        pdaSampleInfo.setSendName(Util.null2String(rs.getString("send_name")));
                        return pdaSampleInfo;
                    }
                });
        return pdaSampleInfoList;
    }


    /**
     * 保存结果至LIS PDA信息，用于PDA标本采集
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg updatePdaStatus(final String ids) throws Exception {
        if (ids == null)
            return new ReturnMsg(0, "参数不能为空");

        //删除样本信息
        String sql = "UPDATE T_Sample_Trans_Time SET FLAG=1" +
                " WHERE InspectionId in ("+ids+") AND flag=0";
        this.lisJdbcTemplate.update(sql);

        return new ReturnMsg(1, "保存成功");
    }
}

