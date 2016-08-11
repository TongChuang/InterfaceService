package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.lis.*;
import com.zcw.webservice.model.vo.*;
import com.zcw.webservice.model.vo.TestResult;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.ws.rs.PathParam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String sql = "select * from vw_testinfo_micro where Barcode ='" + barcode + "'";
        List<TestInfo> testInfoList = null;
        testInfoList = lisJdbcTemplate.query(sql,
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
                           // tmpItemCode = tmpItemCode.substring(0, tmpItemCode.lastIndexOf(","));
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
                        return testInfo;
                    }
                });
        return testInfoList;
        //List<TestInfo> list = lisJdbcTemplate.query(sql, new BeanPropertyRowMapper<TestInfo>(TestInfo.class));
    }

    /**
     * 细菌列表
     *
     * @return
     */
    public List<Bacteria> getBacteriaList() throws Exception {
        List<Bacteria> bacterias = null;
        String sql ="SELECT dh, bh, mc, ywm FROM xj_xjzl";
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
    public List<SampleType>  getSampleTypeList() throws Exception {
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
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(lisJdbcTemplate.getDataSource());
//        TransactionStatus status = transactionManager.getTransaction(def);
        List<TestInfo> testInfoList = null;
        String sql = "select  * from vw_testinfo_micro where status='1' and  CollectDate>='" + signStartDate + "' and CollectDate<='" + signEndDate + "'";

        testInfoList = lisJdbcTemplate.query(sql,
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
    public ReturnMsg saveTestResult(Report info) throws Exception {
        ReturnMsg msg  =new ReturnMsg();
        String patientId = Util.null2String(info.getSampleInfo().getPatientId());           //就诊卡号
        String patientName = Util.null2String(info.getSampleInfo().getPatientName());       //病人姓名
        String partientCode = Util.null2String(info.getSampleInfo().getPatientCode());        //住院号、门诊号
        String barcode = Util.null2String(info.getSampleInfo().getBarcode());               //条码号
        String sampleType = Util.null2String(info.getSampleInfo().getSampleType());        //样本类型
        String sex = Util.null2String(info.getSampleInfo().getSex());                      //性别

        String sql = "";
        log.info(JSON.toJSONString(info));

        if (info == null){
            return new ReturnMsg(0,"参数不能为空！","");
        }
        //判断病人是否相同
        sql = "select count(0) as cnt from f_k_ybxx " +
                "where ybid =? and brxm=? and brxb =? and bbzl=? and brbq=?";
        log.info(sql);
        Long count = lisJdbcTemplate.queryForObject(sql, new Object[]{barcode, patientName, sex, sampleType, partientCode}, Long.class);
        if (count <= 0) {
            log.info("没有记录或病人信息不一致！");
            return new ReturnMsg(0,"没有记录或病人信息不一致！","");
        }
        //内毒素、真菌D 写入LIS普通记录表
        //培养鉴定结果批量保存至微生物结果表
        //1)、写入细菌样本信息表
        sql = "insert into xj_ybxx(ybid,ybbh,cdrq,brxm,brxb," +
                "nl,nllx,brkb,brch,bblx,cyrq,lczd,sjys,jyys,shys,ylxh" +
                ",jymd,bgrq,blh,brbq,kdhs,brphone,createrq,papersize,yqdh) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        final SampleInfo sampleInfo = info.getSampleInfo();
        lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setObject(1, sampleInfo.getBarcode());           //条码号
                ps.setObject(2, sampleInfo.getSamleId());          //样本ID
                ps.setObject(3, sampleInfo.getTestDateTime());      //测定日期
                ps.setObject(4, sampleInfo.getPatientName());       //病人姓名
                ps.setObject(5, sampleInfo.getSex());               //病人性别
                ps.setObject(6, sampleInfo.getAge());               //年龄
                ps.setObject(7, sampleInfo.getAgeType());           //年龄类型
                ps.setObject(8, sampleInfo.getDepartment());       //病人科别
                ps.setObject(9, sampleInfo.getBedNo());             //病人床号
                ps.setObject(10, sampleInfo.getSampleType());        //标本类型
                ps.setObject(11, sampleInfo.getSamplingTime());      //采样时间
                ps.setObject(12, sampleInfo.getClinicalDiagnosis()); //临床诊断
                ps.setObject(13, sampleInfo.getInspectDoctor());     //送检医生
                ps.setObject(14, sampleInfo.getTestDoctor());        //检验医生
                ps.setObject(15, sampleInfo.getAuditDoctor());       //审核医生
                ps.setObject(16, sampleInfo.getTestDestinationNo());   //检验目的编号
                ps.setObject(17, sampleInfo.getTestDestinationName());  //检验目的
                ps.setObject(18, sampleInfo.getReportDateTime());       //报告日期
                ps.setObject(19, sampleInfo.getPatientCode());          //病历号
                ps.setObject(20, sampleInfo.getPatientFileNo());     //病人档案号
                ps.setObject(21, sampleInfo.getBillDepartment());    //开单科室
                ps.setObject(22, sampleInfo.getPatientPhone());      //病人电话
                ps.setObject(23, sampleInfo.getCreateTime());        //创建日期
                ps.setObject(24, "A5");
                ps.setObject(25, "微生物");                         //仪器代号
            }
        });
        //2、保存结果测定值
        final List<TestResult> results = info.getResults();
        if (info.getReportType() == 0) {
            //普通报告
            sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg,j1) values(?,?,?,?,?,?,?,?)";
            this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    int length = results.size();
                    ps.getConnection().setAutoCommit(false);
                    for (int i = 0; i < length; i++) {
                        ps.setString(1, sampleInfo.getSamleId());               //样本号
                        ps.setString(2, results.get(i).getResultType());        //结果类型
                        ps.setInt(3, results.get(i).getResultTypeId());         //结果类型序号
                        ps.setString(4, results.get(i).getResult());             //结果
                        ps.setString(5, results.get(i).getCount());             //菌量计数
                        // ps.setString(5, results.get(i).getDrugResistance());  //耐药标志
                        ps.addBatch();
                    }
                    Object o = ps.executeBatch();
                    ps.getConnection().commit();
                    ps.getConnection().setAutoCommit(true);
                    //如果用<aop:config>  来控制事务，需要把上一行注掉，否则会报错
                    return o;
                }
            });

            //更新药敏信息
            final List<DrugResult> drugResults = info.getDrugResults();
            if (drugResults.size() > 0) {
                sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg,jg1,jg2) values(?,?,?,?,?,?)";
                this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                    public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        int length = drugResults.size();
                        ps.getConnection().setAutoCommit(false);
                        for (int i = 0; i < length; i++) {
                            ps.setString(1, sampleInfo.getSamleId());               //样本号
                            ps.setString(2, "ym");                                   //结果类型
                            ps.setInt(3, 1);                                        //结果类型序号
                            ps.setString(4, drugResults.get(i).getName());             //结果(抗生素名称)
                            ps.setString(5, drugResults.get(i).getAbnormalResult());   //异菌范围
                            ps.setString(6, drugResults.get(i).getReference());        //异菌范围
                            ps.addBatch();
                        }
                        Object o = ps.executeBatch();
                        ps.getConnection().commit();
                        ps.getConnection().setAutoCommit(true);
                        return o;
                    }
                });
            }
        } else if (info.getReportType() == 1) {
            //真菌D内毒素报告
            sql = "insert into lis_xmcdz(yqdh,cdrq,ybbh,xmdh,xmbh,xmcdz,gdbj,ckz,dw) values(?,?,?,?,?,?,?,?)";
            this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    int length = results.size();
                    ps.getConnection().setAutoCommit(false);
                    for (int i = 0; i < length; i++) {
                        ps.setString(1, "微生物");               //仪器代号
                        ps.setDate(2, new java.sql.Date(sampleInfo.getReportDateTime().getTime())); //测定日期
                        ps.setString(3, sampleInfo.getSamleId());                                   //样本编号
                        ps.setString(4, results.get(i).getTestItemCode());                          //结果类型序号
                        ps.setString(5, results.get(i).getTestItemCode());                          //结果
                        ps.setString(6, results.get(i).getResult());                                //菌量计数
                        ps.setString(7, results.get(i).getAbnormalFlag());                          //异常标志
                        ps.setString(8, results.get(i).getReference());                             //参考值
                        ps.setString(9, results.get(i).getUnit());                                   //单位
                        ps.addBatch();
                    }
                    Object o = ps.executeBatch();
                    ps.getConnection().commit();
                    ps.getConnection().setAutoCommit(true);
                    //如果用<aop:config>  来控制事务，需要把上一行注掉，否则会报错
                    return o;
                }
            });
        }
        return new ReturnMsg(1,"保存成功","");
    }


}

