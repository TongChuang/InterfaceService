package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.his.Ward;
import com.zcw.webservice.model.lis.*;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

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
public class LisInfoDao {

    @Autowired
    private  JdbcTemplate lisJdbcTemplate;        //LIS系统连接池

    /**
     * 获取检验信息
     * @param barcode   //申请条码
     * @return
     */
    public String getTestInfo(String barcode){
        //SELECT * FROM t_lis_sampletransPro where ybid ='' and Trans='已计费' 已经计费 状态
        String sql ="select * from vw_testinfo_micro where Barcode ='" + barcode +"'";
        Object[] params = new Object[] { };
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
                        String tmpItemCode =Util.null2String(rs.getString("InspectItemCode"));
                        tmpItemCode = tmpItemCode.substring(0,tmpItemCode.lastIndexOf(","));
                        TestItem testItem = new TestItem();
                        testItem.setId(tmpItemCode);
                        testItem.setCode(tmpItemCode);
                        testItem.setName(Util.null2String(rs.getString("InspectItemName")));
                        List<TestItem> testItems =  new ArrayList<TestItem>();
                        testItems.add(testItem);
                        testInfo.setTestItems(testItems);
                        testInfo.setRemark(Util.null2String(rs.getString("Remark")));
                        testInfo.setSampleNo(Util.null2String(rs.getString("SampleNo")));
                        return testInfo;
                    }
                });

        return JSON.toJSONString(testInfoList);
        //List<TestInfo> list = lisJdbcTemplate.query(sql, new BeanPropertyRowMapper<TestInfo>(TestInfo.class));
    }
    /**
     * 细菌列表
     * @return
     */
    public String getBacteriaList() throws Exception{
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> list = lisJdbcTemplate.queryForList("SELECT dh, bh, mc, ywm FROM xj_xjzl");
        for(Map<String, Object> map:list){
            JSONObject obj = new JSONObject();
            obj.put("Code", Util.null2String(map.get("dh")));
            obj.put("Name",Util.null2String(map.get("mc")));
            obj.put("Alias",Util.null2String(map.get("ywm")));
            jsonArray.put(obj);
        }
        return jsonArray.toString();
    }
    /**
     * 药敏列表信息
     * @return
     */
    public String getDrugList()throws Exception{
        String sql = "SELECT dh, bh, mc, ywm FROM xj_ymzl";
        List<Map<String, Object>> list = lisJdbcTemplate.queryForList(sql);
        JSONArray jsonArray = new JSONArray();
        for(Map<String, Object> map:list){
            JSONObject obj = new JSONObject();
            obj.put("Code", Util.null2String(map.get("dh")));
            obj.put("Name",Util.null2String(map.get("mc")));
            obj.put("Alias",Util.null2String(map.get("ywm")));
            jsonArray.put(obj);
        }
        return jsonArray.toString();
    }

    /**
     * 检验目的列表信息
     * @return
     */
    public String getTestPurposeList()throws Exception{
        String sql = "SELECT t1.jymddh, t1.jymdmc, t1.jymdsf,t2.dh,t2.mc " +
                " FROM xt_jymd t1 left join xt_bbzl t2 on t1.bbzl = t2.dh" +
                " where t1.jyyq =',微生物,' ";
        List<Map<String, Object>> list = lisJdbcTemplate.queryForList(sql);
        JSONArray jsonArray = new JSONArray();
        for(Map<String, Object> map:list){
            JSONObject obj = new JSONObject();
            obj.put("Code", Util.null2String(map.get("jymddh")));
            obj.put("Name",Util.null2String(map.get("jymdmc")));
            obj.put("Fee",Util.null2String(map.get("jymdsf")));
            obj.put("SampleId",Util.null2String(map.get("dh")));
            obj.put("SampleName",Util.null2String(map.get("mc")));
            jsonArray.put(obj);
        }
        return jsonArray.toString();
    }

    /**
     * 标本类型列表信息
     * @return
     */
    public String getSampleTypeList()throws Exception{
        String sql = "SELECT dh,mc FROM xt_bbzl";
        List<Map<String, Object>> list = lisJdbcTemplate.queryForList(sql);
        JSONArray jsonArray = new JSONArray();
//        for(Map<String, Object> map:list){
//            JSONObject obj = new JSONObject();
//            obj.put("Code", Util.null2String(map.get("dh")));
//            obj.put("Name",Util.null2String(map.get("mc")));
//            jsonArray.put(obj);
//        }
        return jsonArray.toString();
    }

    /**
     * 标本类型列表信息
     * @return
     */
    public String getPatientTypeList()throws Exception{
        String sql = "select dh,mc from v_xt_sjlb";
        List<Map<String, Object>> list = lisJdbcTemplate.queryForList(sql);
        JSONArray jsonArray = new JSONArray();
        for(Map<String, Object> map:list){
            JSONObject obj = new JSONObject();
            obj.put("Code", Util.null2String(map.get("dh")));
            obj.put("Name",Util.null2String(map.get("mc")));
            jsonArray.put(obj);
        }
        return jsonArray.toString();
    }

    /**
     *  LIS已签收标本信息
     * @param signStartDate
     * @param signEndDate
     * @return
     */
    public  String getReceivedSampleList(String signStartDate, String signEndDate){
        //and groupdes=',4,'
        String sql = "select  * from vw_testinfo_micro where status='1' and  CollectDate>='"+signStartDate+"' and CollectDate<='"+signEndDate+"'";
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
                        String tmpItemCode =Util.null2String(rs.getString("InspectItemCode"));
                        tmpItemCode = tmpItemCode.substring(0,tmpItemCode.lastIndexOf(","));
                        TestItem testItem = new TestItem();
                        testItem.setId(tmpItemCode);
                        testItem.setCode(tmpItemCode);
                        testItem.setName(Util.null2String(rs.getString("InspectItemName")));
                        List<TestItem> testItems =  new ArrayList<TestItem>();
                        testItems.add(testItem);
                        testInfo.setTestItems(testItems);
                        testInfo.setRemark(Util.null2String(rs.getString("Remark")));
                        testInfo.setSampleNo(Util.null2String(rs.getString("SampleNo")));
                        return testInfo;
                    }
                });
        return JSON.toJSONString(testInfoList);

    }


    /**
     * 保存检测结果至LIS系统
     * @param info
     * @return
     * @throws Exception
     */
    public String saveTestResult(final TestResult info) throws Exception{
        String patientId = Util.null2String(info.getSampleInfo().getPatientId());           //就诊卡号
        String patientName = Util.null2String(info.getSampleInfo().getPatientName());       //病人姓名
        String partientCode=Util.null2String(info.getSampleInfo().getPatientCode());        //住院号、门诊号
        String barcode = Util.null2String(info.getSampleInfo().getBarcode());               //条码号
        String sampleType  = Util.null2String(info.getSampleInfo().getSampleType());        //样本类型
        String sex  = Util.null2String(info.getSampleInfo().getSex());                      //性别
        String sql = "";
        System.out.println(JSON.toJSONString(info));

        if(info != null)
            return "";

        //判断病人是否相同
        sql ="select count(0) as cnt from f_k_ybxx " +
                "where ybid =? and brxm=? and xb =? and sex=? and bbzl=? and brbq=?";
        Long count = lisJdbcTemplate.queryForObject(sql,new Object[]{barcode,patientName,sex,sampleType,partientCode},Long.class);

        if(count<=0){
            return "没有记录或病人信息不一致！";
        }

        //判断记录是否存在
        sql ="select count(0) as cnt from f_k_ybxx " +
                "where ybid =? and brxm=? and xb =? and sex=? and bbzl=? and brbq=?";
        count = lisJdbcTemplate.queryForObject(sql,new Object[]{barcode,patientName,sex,sampleType,partientCode},Long.class);

        //内毒素、真菌D 写入LIS普通记录表
        //培养鉴定结果批量保存至微生物结果表
        //1)、写入细菌样本信息表
        sql = "insert into xj_ybxx(ybid,ybbh,cdrq,brxm,brxb," +
                "nl,nllx,brkb,brch,bblx,cyrq,lczd,sjys,jyys,shys,ylxh" +
                ",jymd,bgrq,blh,brbq,kdhs,brphone,createrq,papersize,yqdh) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg,jg1,jg2,nyjz,kbvalue) values(?,?,?,?,?,?,?,?)";

        final SampleInfo sampleInfo = info.getSampleInfo();
        lisJdbcTemplate.update(sql, new PreparedStatementSetter() {
        @Override
        public void setValues(PreparedStatement ps) throws SQLException {
            ps.setObject(1, sampleInfo.getBarcode());           //条码号
            ps.setObject(1,  sampleInfo.getSamleId());          //样本ID
            ps.setObject(1, sampleInfo.getTestDateTime());      //测定日期
            ps.setObject(1, sampleInfo.getPatientName());       //病人姓名
            ps.setObject(1, sampleInfo.getSex());               //病人性别
            ps.setObject(1, sampleInfo.getAge());
            ps.setObject(1, sampleInfo.getAgeType());
            ps.setObject(1, sampleInfo.getAgeType());
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
            ps.setObject(1, "name4");
        }});

        //2、保存结果测定值
        final List<TestResultDetail> testResultDetails = info.getTestResultDetailList();
        sql = "insert into xj_xmcdz(ybbh,jglx,lxxh,ybjg,jg1,jg2,nyjz,kbvalue) values(?,?,?,?,?,?,?,?)";
        int[] counts = (int[]) this.lisJdbcTemplate.execute(sql, new PreparedStatementCallback(){
            public Object doInPreparedStatement(PreparedStatement ps)throws SQLException, DataAccessException {
                int length = testResultDetails.size();
                ps.getConnection().setAutoCommit(false);
                for(int i=0;i<length;i++){
                    ps.setString(1, testResultDetails.get(i).getSampleNo());            //样本号
                    ps.setString(2, testResultDetails.get(i).getResultType());          //结果类型
                    ps.setInt(3, testResultDetails.get(i).getResultTypeId());           //结果类型序号
                    ps.setString(4, testResultDetails.get(i).getResult());              //结果
                    ps.setString(5, testResultDetails.get(i).getAbnormalResult());      //异菌结果
                    ps.setString(6, testResultDetails.get(i).getResultValue());         //结果值(R/S/I)
                    ps.setString(7, testResultDetails.get(i).getDrugResistance());      //耐药机制
                    ps.setString(8, testResultDetails.get(i).getReference());           //kb法结果范围
                    ps.addBatch();
                }
                Object o = ps.executeBatch();
                ps.getConnection().commit();
                ps.getConnection().setAutoCommit(true);
                //如果用<aop:config>  来控制事务，需要把上一行注掉，否则会报错
                return o;
            }});

        return "";
    }


}

