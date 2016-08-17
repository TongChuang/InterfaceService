package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.his.*;
import com.zcw.webservice.model.vo.ReturnMsg;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Title: HisInfoDao
 * Description:HISDAO
 *
 * @author:zhou
 * @date:2016/8/3 11:46
 * @version:
 */
@Repository
public class HisInfoDao extends BaseDao {
    private static Logger log = Logger.getLogger(LisInfoDao.class);

    /**
     * 获取His病区信息
     *
     * @return
     */
    public String getWardList() throws Exception {
        String sql = "select * from V_HSBHI_WARDINFO";
        List<Map<String, Object>> list = hisJdbcTemplate.queryForList(sql);
        Object[] params = new Object[]{};
        List<Ward> wardInfoList = null;
        wardInfoList = hisJdbcTemplate.query(sql,
                new RowMapper<Ward>() {
                    public Ward mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Ward wardInfo = new Ward();
                        wardInfo.setId(Util.null2String(rs.getString("ZZKSID")));
                        wardInfo.setCode(Util.null2String(rs.getString("ZZKSDM")));
                        wardInfo.setName(Util.null2String(rs.getString("ZZKSMC")));
                        wardInfo.setDepartment(Util.null2String(rs.getString("ZZJGDM")));
                        return wardInfo;
                    }
                });

        return JSON.toJSONString(wardInfoList);
    }

    /**
     * 获取His科室信息
     *
     * @return
     */
    public List<Department> getDepartmentList() throws Exception {
        String sql = "select * from V_HSBHI_DEPTINFO";
        List<Map<String, Object>> list = hisJdbcTemplate.queryForList(sql);
        Object[] params = new Object[]{};
        List<Department> departmentList = null;
        departmentList = hisJdbcTemplate.query(sql,
                new RowMapper<Department>() {
                    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Department department = new Department();
                        department.setId(Util.null2String(rs.getString("ZZKSID")));
                        department.setParentId(Util.null2String(rs.getString("FLKSID")));
                        department.setCode(Util.null2String(rs.getString("ZZKSDM")));
                        department.setName(Util.null2String(rs.getString("ZZKSMC")));
                        return department;
                    }
                });

        return departmentList;
    }

    /**
     * 病人信息
     *
     * @param patientType 病人类别:住院、门诊..
     * @param patientCode 住院、门诊号
     * @return 返回病人信息
     */
    public List<Patient> getPatientInfo(String patientType, String patientCode) {
        if (patientType.equals("1")) {
            //住院病人信息
            return getInPatientInfo(patientCode);
        }
        if (patientType.equals("2")) {
            //门诊病人信息
            return getOutPatientInfo(patientCode);
        }
        return null;
    }

    /**
     * 获取住院病人信息
     *
     * @param patientCode
     * @return 返回住院病人信息
     */
    private List<Patient> getInPatientInfo(String patientCode) {
        List<Patient> patientList = null;
        String sql = "select * from V_HSBBI_RECORDHOME where BRJZHM =?";
        patientList = hisJdbcTemplate.query(sql,new Object[]{patientCode},
                new RowMapper<Patient>() {
                    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Patient patient = new Patient();
                        patient.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        patient.setParentId(Util.null2String(rs.getString("BRZYID")));
                        patient.setName(Util.null2String(rs.getString("BRDAXM")));
                        patient.setSex(Util.null2String(rs.getString("BRDAXB")));
                        patient.setBirthday(Util.null2String(rs.getString("BRCSRQ")));
                        patient.setAge(Util.null2String(rs.getString("BRJZNL")));
                        patient.setDepartment("");
                        patient.setDoctor(Util.null2String(rs.getString("ZZYSID")));
                        patient.setCompany(Util.null2String(rs.getString("BRDWMC")));
                        patient.setIdCard(Util.null2String(rs.getString("BRSFZH")));
                        patient.setAdmissionDepartment(Util.null2String(rs.getString("RYKSID")));
                        patient.setHospitalWard(Util.null2String(rs.getString("RYBQID")));
                        patient.setInpatientDepartment(Util.null2String(rs.getString("ZYKSID")));
                        patient.setInpatientWard(Util.null2String(rs.getString("ZYBQID")));
                        patient.setBedno(Util.null2String(rs.getString("ZYCWHM")));
                        patient.setStatus(Util.null2String(rs.getString("BRZYZT")));
                        patient.setPatientType(Util.null2String(rs.getString("BRLBID")));
                        patient.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                        patient.setChargeType(Util.null2String(rs.getString("BRLBID")));
                        return patient;
                    }
                });

        return patientList;
    }

    /**
     * 获取门诊病人信息
     *
     * @param patientCode
     * @return 返回门诊病人信息
     */
    private List<Patient> getOutPatientInfo(String patientCode) {
        List<Patient> patientList = null;
        String sql = "select * from V_HSBCI_TREATINFO where BRJZHM =?";
        patientList = hisJdbcTemplate.query(sql,new Object[]{patientCode},
                new RowMapper<Patient>() {
                    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Patient patient = new Patient();
                        patient.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        patient.setChargeType(Util.null2String(rs.getString("BRLBID")));
                        patient.setName(Util.null2String(rs.getString("BRDAXM")));
                        patient.setSex(Util.null2String(rs.getString("BRDAXB")));
                        patient.setBirthday(Util.null2String(rs.getString("BRCSRQ")));
                        patient.setAge(Util.null2String(rs.getString("BRJZNL")));
                        patient.setDepartment(Util.null2String(rs.getString("JZKSID")));
                        patient.setDoctor(Util.null2String(rs.getString("JZYSID")));
                        patient.setCompany(Util.null2String(rs.getString("BRDWMC")));
                        patient.setIdCard(Util.null2String(rs.getString("BRSFZH")));
//                        patient.setAdmissionDepartment(Util.null2String(rs.getString("RYKSID")));
//                        patient.setHospitalWard(Util.null2String(rs.getString("RYBQID")));
//                        patient.setInpatientDepartment(Util.null2String(rs.getString("ZYKSID")));
//                        patient.setInpatientWard(Util.null2String(rs.getString("ZYBQID")));
//                        patient.setBedno(Util.null2String(rs.getString("ZYCWHM")));
//                        patient.setStatus(Util.null2String(rs.getString("BRZYZT")));
                        patient.setPatientType(Util.null2String(rs.getString("BRLBID")));
                        patient.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                        return patient;
                    }
                });

        return patientList;
    }

    /**
     * MILS 补计费、退费
     *
     * @param accountItem
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg saveBooking(final AccountItem accountItem) {
        ReturnMsg msg = new ReturnMsg();
        List<String> userids = lisJdbcTemplate.queryForList("select his_id from xt_user where logid=?", new Object[]{accountItem.getOperatorNo()},String.class);
        if (userids.size()<=0) {
            return new ReturnMsg(0,"HIS用户不存在，请检查。");
        }
        final String hisUserID = userids.get(0);
        String sql = "select  ETRACKHIS.SEQ_II_INPATICHARGE_JZJLID.Nextval as id from dual";
        final Long seqId = hisJdbcTemplate.queryForObject(sql, Long.class);
        accountItem.setAccountId(seqId);
        //获取病人唯一号
        List<Patient> patientList = getPatientInfo(accountItem.getPatientType(),accountItem.getPatientCode());
        if(patientList.size()<=0){
            return new ReturnMsg(0,"病人信息不存在，请检查。");
        }
        final  String patientId = patientList.get(0).getParentId();

        //插入收费记录
        sql = "insert into II_INPATICHARGE(JZJLID,BRZYID,YPZLPB,FYXMID,FYTJID," +
                "FYFSRQ,FYFSSL,KDYSID,KDKSID,ZXYHID,ZXKSID,CZYHID)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            hisJdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, accountItem.getAccountId());                                       //记账记录序号
                    ps.setObject(2, patientId);          //病人住院序号
                    // ps.setObject(3, accountItem.pa());                       //组织机构代码
                    ps.setLong(3, 2);                                           //药品诊疗判别 1 药品 2 诊疗
                    ps.setString(4, accountItem.getFeeItemCode());              //费用项目序号 代码11266
                    //ps.setObject(6, accountItem.getAge());                    //药品产地序号 诊疗不需要，药品需传入
                    ps.setLong(5, 14);                                          //费用途径序号 12 用血 14 LIS 15 物资
                    ps.setDate(6, Util.toSqlDate(accountItem.getDateTime()));   //费用发生日期 日期 yyyy-mm-dd hh24:mi:ss
                    ps.setInt(7, accountItem.getQuantity());                    //费用发生数量
                    ps.setString(8, hisUserID);                                 //开单医生序号
                    ps.setString(9, "21");                                      //开单科室序号
                    ps.setString(10, hisUserID);                                //执行用户序号
                    ps.setString(11, "21");                                     //执行科室序号
                    ps.setString(12, hisUserID);                                //操作用户序号
                }
            });
            return new ReturnMsg(1, accountItem);
        } catch (Exception e) {
            log.error("计费异常", e);
            msg.setState(0);
            msg.setMessage("计费异常:" + e.getMessage());
        }
        return msg;
    }

    public ReturnMsg requestUpdate(final RequestUpdateParam param){

        int appCode=-1;
        String outData ="";

        String sql = "{call Etrack_Interface.Prc_RequestUpdate(?,?,?,?,?,?,?,?,?)}";
        Object obj = hisJdbcTemplate.execute(sql,
                new CallableStatementCallback() {
                    public Object[] doInCallableStatement(CallableStatement cs)
                            throws SQLException, DataAccessException {
//前面9个是输入参数，后面4个是输出参数
                        cs.setInt(1, param.getRequestType());
                        cs.setString(2, param.getItemId());
                        cs.setInt(3, param.getExeType());
                        cs.setString(4, param.getExeDeptCode());
                        cs.setString(5, "PREMIUMMODE_Y");
                        cs.setString(6, "C042525");
                        cs.setDouble(7, 45);
                        cs.setInt(8, 1);
                        cs.setString(9, null);
                        cs.registerOutParameter(10, OracleTypes.VARCHAR);
                        cs.registerOutParameter(11, Types.VARCHAR);
                        cs.registerOutParameter(12, Types.FLOAT);
                        cs.registerOutParameter(13, Types.FLOAT);
                        cs.execute();
                        return new Object[] { cs.getString(10),
                                cs.getString(11), cs.getDouble(12),
                                cs.getDouble(13) };
                    }
                });

        return null;
    }

    /**
     * 病人申请信息
     * @param patientId
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<PatientRequestInfo> getPatientRequestInfo(String patientType, String patientId,String fromDate, String toDate) {

        if (patientType.equals("2")) {
            //住院病人信息
            return getInPatientRequestInfo(patientId,fromDate, toDate);
        }
        if (patientType.equals("1")) {
            //门诊病人信息
            return getOutPatientRequestInfo(patientId,fromDate, toDate);
        }
        return null;
    }

    /**
     * 住院病人申请信息
     * @param patientId
     * @param fromDate
     * @param toDate
     * @return
     */
    private List<PatientRequestInfo> getInPatientRequestInfo(final String patientId, final String fromDate, final String toDate) {
        List<PatientRequestInfo> patientRequestInfoList = null;
        String sql = "select * from V_HSBDI_REQUESTINFO where  BRZYID=? and BRSQLX=1 and  (SQKDRQ>=to_date(?,'yyyy-MM-dd') and SQKDRQ<=to_date(?,'yyyy-MM-dd')) ";
        patientRequestInfoList = hisJdbcTemplate.query(sql,
                new Object[]{patientId,fromDate,toDate},
                new RowMapper<PatientRequestInfo>() {
                    public PatientRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientRequestInfo info = new PatientRequestInfo();
                        info.setRequestId(Util.getLongValue(rs.getString("SQJLID")));
                        info.setRequestDetailId(Util.getLongValue(rs.getString("SQMXID")));
                        info.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        info.setParentId(Util.null2String(rs.getString("BRZYID")));
                        info.setPartientRequestCode(Util.null2String(rs.getString("BRSQHM")));
                        info.setName(Util.null2String(rs.getString("BRDAXM")));
                        info.setSex(Util.null2String(rs.getString("BRDAXB")));
                        info.setBirthday(Util.null2String(rs.getString("BRCSRQ")));
                        info.setDepartment(Util.null2String(rs.getString("DQKSID")));
                        info.setWard(Util.null2String(rs.getString("DQBQID")));
                        info.setBedno(Util.null2String(rs.getString("BQCWHM")));
                        info.setDiagnose(Util.null2String(rs.getString("JBZDMC")));
                        info.setRequestType(Util.null2String(rs.getString("BRSQLX")));
                        info.setRequestItemType(Util.null2String(rs.getString("SQXMLX")));
                        info.setRequestDoctor(Util.null2String(rs.getString("KDYSID")));
                        info.setRequestDepartment(Util.null2String(rs.getString("KDKSID")));
                        info.setRequestDateTime(rs.getDate("SQKDRQ"));
                        info.setItemCode(Util.null2String(rs.getString("JCXMID")));
                        info.setItemName(Util.null2String(rs.getString("JCXMMC")));
                        info.setItemPrintName(Util.null2String(rs.getString("XMDYMC")));
                        info.setQuantity(Util.getIntValue(rs.getString("XMSQSL")));
                        info.setStatus(Util.getIntValue(rs.getString("SQZTBZ")));
                        info.setTestDept(Util.null2String(rs.getString("ZXKSID")));
                        info.setEmergency(Util.getIntValue(rs.getString("SFJZPB")));
                        info.setAmount((rs.getFloat("FYHJJE")));
                        info.setTestPart(Util.null2String(rs.getString("ZLBWMC")));
                        info.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                        return info;
                    }
                });

        return patientRequestInfoList;
    }
    /**
     * 门诊病人申请信息
     * @param patientId
     * @param fromDate
     * @param toDate
     * @return
     */
    private List<PatientRequestInfo> getOutPatientRequestInfo(String patientId, String fromDate, String toDate) {
        List<PatientRequestInfo> patientRequestInfoList = null;
        String sql = "select * from V_HSBCI_REQUESTINFO where BRJZHM =?  and BRSQLX=1 and  (SQKDRQ>=to_date(?,'yyyy-MM-dd') and SQKDRQ<=to_date(?,'yyyy-MM-dd')) ";
        patientRequestInfoList = hisJdbcTemplate.query(sql,
                new Object[]{patientId,fromDate,toDate},
                new RowMapper<PatientRequestInfo>() {
                    public PatientRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientRequestInfo info = new PatientRequestInfo();
                        info.setRequestId(Util.getLongValue(rs.getString("SQJLID")));
                        info.setRequestDetailId(Util.getLongValue(rs.getString("SQMXID")));
                        info.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        info.setParentId(Util.null2String(rs.getString("BRJZXH")));
                        info.setPartientRequestCode(Util.null2String(rs.getString("BRSQHM")));
                        info.setName(Util.null2String(rs.getString("BRDAXM")));
                        info.setSex(Util.null2String(rs.getString("BRDAXB")));
                        info.setBirthday(Util.null2String(rs.getString("BRCSRQ")));
                        //info.setDepartment(Util.null2String(rs.getString("DQKSID")));
                        info.setDiagnose(Util.null2String(rs.getString("JBZDMC")));
                        info.setRequestType(Util.null2String(rs.getString("BRSQLX")));
                        //info.setRequestItemType(Util.null2String(rs.getString("SQXMLX")));
                        info.setRequestDoctor(Util.null2String(rs.getString("KDYSID")));
                        info.setRequestDepartment(Util.null2String(rs.getString("KDKSID")));
                        info.setRequestDateTime(rs.getDate("SQKDRQ"));
                        info.setItemCode(Util.null2String(rs.getString("JCXMID")));
                        info.setItemName(Util.null2String(rs.getString("JCXMMC")));
                        info.setItemPrintName(Util.null2String(rs.getString("XMDYMC")));
                        info.setQuantity(Util.getIntValue(rs.getString("XMSQSL")));
                        info.setStatus(Util.getIntValue(rs.getString("SQZTBZ")));
                        info.setTestDept(Util.null2String(rs.getString("ZXKSID")));
                        info.setEmergency(Util.getIntValue(rs.getString("SFJZPB")));
                        info.setAmount((rs.getFloat("FYHJJE")));
                        info.setTestPart(Util.null2String(rs.getString("ZLBWMC")));
                        info.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                        return info;
                    }
                });

        return patientRequestInfoList;
    }
}
