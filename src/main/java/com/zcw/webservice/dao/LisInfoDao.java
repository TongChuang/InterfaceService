package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.lis.*;
import com.zcw.webservice.model.vo.*;
import com.zcw.webservice.model.vo.TestResult;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<TestInfo> getTestInfo(String barcode) {
        //SELECT * FROM t_lis_sampletransPro where ybid ='' and Trans='已计费' 已经计费 状态
        String sql = "select * from vw_testinfo_micro where Barcode =?";
        List<TestInfo> testInfoList = null;
        testInfoList = lisJdbcTemplate.query(sql,new Object[]{barcode},
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
                        if (!tmpItemCode.equals("")) {
                            if (tmpItemCode.lastIndexOf(",") > 0) {
                                tmpItemCode = tmpItemCode.substring(0, tmpItemCode.lastIndexOf(","));
                            }
                            TestItem testItem = new TestItem();
                            testItem.setId(tmpItemCode);
                            testItem.setCode(tmpItemCode);
                            testItem.setName(Util.null2String(rs.getString("InspectItemName")));
                            List<TestItem> testItems = new ArrayList<TestItem>();
                            testItems.add(testItem);
                            testInfo.setTestItems(testItems);
                        }
                        testInfo.setRemark(Util.null2String(rs.getString("Remark")));
                        testInfo.setSampleNo(Util.null2String(rs.getString("SampleNo")));
                        testInfo.setPatientFileNo(Util.null2String(rs.getString("patientFileNo")));
                        testInfo.setPatientPhone(Util.null2String(rs.getString("patientPhone")));
                        testInfo.setIsToll(Util.null2String(rs.getString("patientPhone")));
                        testInfo.setPatientId(Util.null2String(rs.getString("patientid")));
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
        String sql = "SELECT dh, bh, mc, ywm FROM xj_xjzl";
        bacterias = lisJdbcTemplate.query(sql,
                new RowMapper<Bacteria>() {
                    public Bacteria mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Bacteria bacteria = new Bacteria();
                        bacteria.setCode(Util.null2String(rs.getString("dh")));
                        bacteria.setName(Util.null2String(rs.getString("mc")));
                        bacteria.setAlias(Util.null2String(rs.getString("ywm")));
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
        String sql = "SELECT t1.jymddh, t1.jymdmc, t1.jymdsf,t2.dh,t2.mc " +
                " FROM xt_jymd t1 left join xt_bbzl t2 on t1.bbzl = t2.dh" +
                " where t1.jyyq =',微生物,' ";
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
    public List<TestInfo> getReceivedSampleList(String signStartDate, String signEndDate) {
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

        if (info.getReportType() == 0) {
            //普通培养报告
            msg = saveTestResult1(info);
        } else {
            //真菌D、内毒素报告
            msg = saveTestResult2(info);
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
                ps.setTimestamp(10, new java.sql.Timestamp(sampleInfo.getSamplingTime().getTime()));      //采样时间
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
                ps.setTimestamp(26, new java.sql.Timestamp(sampleInfo.getSamplingTime().getTime()));
            }
        });

        //普通报告
        final List<TestResult> results = report.getResults();
        sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg) values(?,?,?,?)";
        this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                int length = results.size();
                for (int i = 0; i < length; i++) {
                    ps.setString(1, sampleInfo.getSampleId());               //样本号
                    ps.setString(2, results.get(i).getResultType());        //结果类型
                    ps.setInt(3, results.get(i).getResultTypeId());         //结果类型序号
                    ps.setString(4, results.get(i).getResult());             //结果
                    //ps.setString(5, results.get(i).getCount());             //菌量计数
                    // ps.setString(5, results.get(i).getDrugResistance());  //耐药标志
                    ps.addBatch();
                }
                Object o = ps.executeBatch();
                return o;
            }
        });
        //异常测试
        if (1 == 1) {
            throw new Exception("错误！！！");
        }
        //更新药敏信息
        final List<DrugResult> drugResults = report.getDrugResults();
        if (drugResults.size() > 0) {
            sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg,jg1,jg2,kbvalue,jgxh) values(?,?,?,?,?,?,?,?)";
            this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    int length = drugResults.size();
                    boolean flag = false;
                    if (length > 0) {
                        int orderNo = Util.getIntValue(drugResults.get(0).getResultCode());
                        if (orderNo > 1) flag = true;
                    }
                    for (int i = 0; i < length; i++) {
                        int OrderId = Util.getIntValue(drugResults.get(i).getResultCode());
                        if (flag) {
                            OrderId--;
                        }
                        ps.setString(1, sampleInfo.getSampleId());               //样本号
                        ps.setString(2, "ym");                                   //结果类型
                        ps.setInt(3, OrderId);        //结果类型序号
                        ps.setString(4, drugResults.get(i).getName());             //结果(抗生素名称)
                        ps.setString(5, drugResults.get(i).getAbnormalResult());   //异菌范围
                        ps.setString(6, drugResults.get(i).getResultValue());        //结果值(R/S/I)
                        ps.setString(7, drugResults.get(i).getReference());        //KB参考范围
                        ps.setString(8, drugResults.get(i).getOrderNo());        //KB参考范围
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
                ",jymdmc,bgrq,brbq,khks,brphone,papersize) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        final SampleInfo sampleInfo = report.getSampleInfo();
        final int sex = (sampleInfo.getSex().equals("女")) ? 2 : 1;
        //保存样本信息
        lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, "微生物");                                                     //仪器代号
                ps.setString(2, sampleInfo.getBarcode());                                       //条码号
                ps.setString(3, sampleInfo.getSampleId());                                       //样本ID
                ps.setString(4, sampleInfo.getPatientCode());                                   //病历号
                ps.setTimestamp(5, new java.sql.Timestamp(sampleInfo.getTestDateTime().getTime()));      //测定日期
                ps.setString(6, sampleInfo.getPatientName());                                   //病人姓名
                ps.setInt(7, sex);                                                              //病人性别
                ps.setObject(8, sampleInfo.getAge());                                           //年龄
                ps.setObject(9, sampleInfo.getAgeType());                                       //年龄类型
                ps.setObject(10, sampleInfo.getDepartment());                                    //病人科别
                ps.setObject(11, sampleInfo.getBedNo());                                         //病人床号
                ps.setObject(12, sampleInfo.getSampleTypeCode());                               //标本类型
                ps.setTimestamp(13, new java.sql.Timestamp(sampleInfo.getSamplingTime().getTime()));      //采样时间
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
            }
        });

        //真菌D内毒素报告
        sql = "insert into lis_xmcdz(yqdh,cdrq,ybbh,xmdh,xmbh,xmcdz,gdbj,ckz,dw) values(?,?,?,?,?,?,?,?,?)";
        final List<TestResult> results = report.getResults();
        this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                int length = results.size();
                //ps.getConnection().setAutoCommit(false);
                for (int i = 0; i < length; i++) {
                    ps.setString(1, "微生物");                                                 //仪器代号
                    ps.setTimestamp(2, new java.sql.Timestamp(sampleInfo.getReportDateTime().getTime())); //测定日期
                    ps.setString(3, sampleInfo.getSampleId());                                   //样本编号
                    ps.setString(4, results.get(i).getTestItemCode());                          //结果类型序号
                    ps.setString(5, results.get(i).getTestItemCode());                          //结果
                    ps.setString(6, results.get(i).getResult());                                //菌量计数
                    ps.setString(7, results.get(i).getAbnormalFlag());                          //异常标志
                    ps.setString(8, results.get(i).getReference());                             //参考值
                    ps.setString(9, results.get(i).getUnit());                                   //单位
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
     * 日志记录
     * @return
     */
    public ReturnMsg saveSampleFlowLog(final SampleLog sampleLog)throws Exception{
        if(sampleLog ==null)
            return new ReturnMsg(0,"参数不能为空");
        if(sampleLog.getSampleNo()==null || sampleLog.getSampleNo().equals(""))
            return new ReturnMsg(0,"样本号不能为空");
        String sql = "insert into t_lis_sampletransPro(ybid,recordtime,operator,trans)values(?,?,?,?) " ;
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
}

