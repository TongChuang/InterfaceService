package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.his.*;
import com.zcw.webservice.model.vo.ReturnMsg;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<Patient> getPatientInfo(String patientType, String patientCode, String patientId) throws Exception{
        if (patientType.equals("1")) {
            //门诊病人信息
            return getOutPatientInfo(patientCode, patientId);
        }
        if (patientType.equals("2")) {
            //住院病人信息
            return getInPatientInfo(patientCode, patientId);
        }
        return null;
    }

    /**
     * 获取住院病人信息
     *
     * @param patientCode
     * @return 返回住院病人信息
     */
    private List<Patient> getInPatientInfo(String patientCode, String patientId) throws Exception{
        List<Patient> patientList = null;
        String sql = "";
        Object[] parms = null;
        if (!patientId.equals("")) {
            sql = "select * from V_HSBBI_RECORDHOME where BRJZHM =? and BRZYID=?";
            parms = new Object[]{patientCode, patientId};
        } else {
            parms = new Object[]{patientCode};
            sql = "select * from(select * from V_HSBBI_RECORDHOME where BRJZHM =? order by BRZYID desc) where rownum<=1";
        }
        patientList = hisJdbcTemplate.query(sql, parms,
                new RowMapper<Patient>() {
                    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Patient patient = new Patient();
                        patient.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        patient.setPatientId(Util.null2String(rs.getString("BRZYID")));
                        patient.setName(Util.null2String(rs.getString("BRDAXM")));
                        patient.setSex(Util.null2String(rs.getString("BRDAXB")));
                        patient.setBirthday(Util.null2String(rs.getString("BRCSRQ")));
                        patient.setAge(Util.null2String(rs.getString("BRJZNL")));
                        patient.setDepartment(Util.null2String(rs.getString("RYKSID"))); //入院科室
                        patient.setDoctor(Util.null2String(rs.getString("ZZYSID")));
                        patient.setCompany(Util.null2String(rs.getString("BRDWMC")));
                        patient.setIdCard(Util.null2String(rs.getString("BRSFZH")));
                        patient.setAdmissionDepartment(Util.null2String(rs.getString("RYKSID")));
                        patient.setHospitalWard(Util.null2String(rs.getString("RYBQID")));
                        patient.setInpatientDepartment(Util.null2String(rs.getString("ZYKSID")));
                        patient.setInpatientWard(Util.null2String(rs.getString("ZYBQID")));
                        patient.setBedno(Util.null2String(rs.getString("ZYCWHM")));
                        patient.setStatus(Util.null2String(rs.getString("BRZYZT")));
                        patient.setPatientType("2");
                        patient.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                        patient.setChargeType(Util.null2String(rs.getString("BRLBID")));
                        patient.setInDateTime(rs.getDate("BRJZRQ"));
                        patient.setPatientPhone(Util.null2String(rs.getString("BRLXDH")));
                        patient.setPatientAddress(Util.null2String(rs.getString("BRJTDZ")));
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

    private List<Patient> getOutPatientInfo(String patientCode, String patientId) throws Exception{
        List<Patient> patientList = null;
        //String sql = "select * from V_HSBCI_TREATINFO where BRJZHM =?";
        String sql = "";

        Object[] parms = null;
        if (!patientId.equals("")) {
            sql = "select * from V_HSBCI_TREATINFO where BRJZHM =?  and BRJZXH=?";
            parms = new Object[]{patientCode, patientId};
        } else {
            parms = new Object[]{patientCode};
            sql = "select * from(select * from V_HSBCI_TREATINFO where BRJZHM =? order by BRJZXH desc) where rownum<=1";
        }
        patientList = hisJdbcTemplate.query(sql, parms,
                new RowMapper<Patient>() {
                    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Patient patient = new Patient();
                        patient.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        patient.setPatientId(Util.null2String(rs.getString("BRJZXH")));
                        patient.setChargeType(Util.null2String(rs.getString("BRLBID")));
                        patient.setName(Util.null2String(rs.getString("BRDAXM")));
                        patient.setSex(Util.null2String(rs.getString("BRDAXB")));
                        patient.setBirthday(Util.null2String(rs.getString("BRCSRQ")));
                        patient.setAge(Util.null2String(rs.getString("BRJZNL")));
                        patient.setDepartment(Util.null2String(rs.getString("JZKSID")));
                        patient.setDoctor(Util.null2String(rs.getString("JZYSID")));
                        patient.setCompany(Util.null2String(rs.getString("BRDWMC")));
                        patient.setIdCard(Util.null2String(rs.getString("BRSFZH")));
                        patient.setInDateTime(rs.getDate("BRJZRQ"));
//                        patient.setAdmissionDepartment(Util.null2String(rs.getString("RYKSID")));
//                        patient.setHospitalWard(Util.null2String(rs.getString("RYBQID")));
//                        patient.setInpatientDepartment(Util.null2String(rs.getString("ZYKSID")));
//                        patient.setInpatientWard(Util.null2String(rs.getString("ZYBQID")));
//                        patient.setBedno(Util.null2String(rs.getString("ZYCWHM")));
//                        patient.setStatus(Util.null2String(rs.getString("BRZYZT")));
                        patient.setPatientType("1");
                        patient.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                        patient.setPatientPhone(Util.null2String(rs.getString("BRLXDH")));
                        patient.setPatientAddress(Util.null2String(rs.getString("BRJTDZ")));
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
    public ReturnMsg saveBooking(final List<AccountItem> accountItem) throws Exception{
        ReturnMsg msg = new ReturnMsg();
        String userids = "";
        try {
            userids = lisJdbcTemplate.queryForObject("select his_id from xt_user where logid=?", new Object[]{accountItem.get(0).getOperatorNo()}, String.class);
        }catch (EmptyResultDataAccessException e){
            return new ReturnMsg(0, "HIS用户不存在，请检查。");
        }
        if(userids.equals("")) return new ReturnMsg(0, "HIS用户不存在，请检查。");
        final String hisUserID = userids;

        String sql = "";
        //插入收费记录
        sql = "insert into II_INPATICHARGE(JZJLID,BRZYID,YPZLPB,FYXMID,FYTJID," +
                "FYFSRQ,FYFSSL,KDYSID,KDKSID,ZXYHID,ZXKSID,CZYHID)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            this.hisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    int length = accountItem.size();
                    for (int i = 0; i < length; i++) {
                        if (accountItem.get(i).getAccountId() == null || accountItem.get(i).getAccountId().equals("")) {
                            String sql_1 = "select  ETRACKHIS.SEQ_II_INPATICHARGE_JZJLID.Nextval as id from dual";
                            final Long seqId = hisJdbcTemplate.queryForObject(sql_1, Long.class);
                            accountItem.get(i).setAccountId(seqId);
                        }
                        ps.setLong(1, accountItem.get(i).getAccountId());                     //记账记录序号
                        ps.setObject(2, accountItem.get(i).getPatientId());                 //病人就诊序号
                        ps.setLong(3, 2);                                           //药品诊疗判别 1 药品 2 诊疗
                        ps.setString(4, accountItem.get(i).getFeeItemCode());              //费用项目序号 代码11266
                        //ps.setObject(6, accountItem.getAge());                    //药品产地序号 诊疗不需要，药品需传入
                        ps.setLong(5, 14);                                          //费用途径序号 12 用血 14 LIS 15 物资
                        ps.setTimestamp(6, new java.sql.Timestamp(accountItem.get(i).getDateTime().getTime()));   //费用发生日期 日期 yyyy-mm-dd hh24:mi:ss
                        ps.setInt(7, accountItem.get(i).getQuantity());                    //费用发生数量
                        ps.setString(8, hisUserID);                                 //开单医生序号
                        ps.setString(9, "21");                                      //开单科室序号
                        ps.setString(10, hisUserID);                                //执行用户序号
                        ps.setString(11, "21");                                     //执行科室序号
                        ps.setString(12, hisUserID);                                //操作用户序号
                        ps.addBatch();
                    }
                    Object o = ps.executeBatch();
                    return o;
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

    /**
     * 申请状态更新
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg requestUpdate(final RequestUpdateParam param) throws Exception {
        String sql = "{call Etrack_Interface.Prc_RequestUpdate(?,?,?,?,?,?,?,?,?)}";
        Map mapR = (Map) hisJdbcTemplate.execute(sql,
                new CallableStatementCallback() {
                    public Map doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                        //前面7个是输入参数，后面2个是输出参数
                        cs.setInt(1, param.getRequestType());
                        cs.setString(2, param.getItemId());
                        cs.setInt(3, param.getExeType());
                        cs.setString(4, param.getExeDeptCode());
                        cs.setString(5, param.getExeDoctorCode());
                        cs.setTimestamp(6, new java.sql.Timestamp(param.getExeDate().getTime()));
                        cs.setString(7, param.getExpand());
                        cs.registerOutParameter(8, OracleTypes.NUMBER);
                        cs.registerOutParameter(9, Types.VARCHAR);
                        cs.execute();
                        Map map = new HashMap();
                        map.put("appCode", cs.getString(8)); // 错误代码 成功返回0  失败返回 -1
                        map.put("outDate", cs.getString(9)); // 错误返回消息
                        return map;
                    }
                });
        if (mapR.get("appCode").equals("0")) {
            return new ReturnMsg(1, "操作成功");
        } else {
            return new ReturnMsg(0, mapR.get("outDate"));
        }

    }

//    /**
//     * 病人申请信息
//     *
//     * @param patientId
//     * @param fromDate
//     * @param toDate
//     * @return
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public List<PatientRequestInfo> getPatientRequestInfo(int requestType, int executeStatus,String patientType, String patientId, String fromDate, String toDate) throws Exception{
//
//        if (patientType.equals("2")) {
//            //住院病人信息
//            return getInPatientRequestInfo(requestType, executeStatus,patientId, fromDate, toDate);
//        }
//        if (patientType.equals("1")) {
//            //门诊病人信息
//            return getOutPatientRequestInfo(requestType, executeStatus,patientId, fromDate, toDate);
//        }
//        return null;
//    }

    /**
     * 住院病人申请信息
     * @param requestType
     * @param executeStatus
     * @param ward
     * @return
     * @throws Exception
     */
    public List<PatientRequestInfo> getInPatientRequestInfo( int requestType, int executeStatus, String ward) throws Exception{
        List<PatientRequestInfo> patientRequestInfoList = null;
        String sql = "select * from V_HSBDI_REQUESTINFO where BRSQLX =?  and DQBQID=?  and SQZTBZ=? ";
        List<Object> parms = new ArrayList<Object>();
        parms.add(requestType);
        parms.add(ward);
        if(requestType==2){
            executeStatus = 0;
        }
        parms.add(executeStatus);
        patientRequestInfoList = hisJdbcTemplate.query(sql,parms.toArray(),
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
     *
     * @param patientId
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<PatientRequestInfo> getOutPatientRequestInfo(int requestType,int executeStatus, String patientId, String fromDate, String toDate) throws Exception{
        List<PatientRequestInfo> patientRequestInfoList = null;
        String sql = "select * from V_HSBCI_REQUESTINFO where BRSQLX =?  and BRJZHM=? and SQZTBZ=? ";
        List<Object> parms = new ArrayList<Object>();
        parms.add(requestType);
        parms.add(patientId);
        if(requestType==2){
            executeStatus = 0;
        }
        parms.add(executeStatus);

        if (!fromDate.equals("")) {
            fromDate += " 00:00:00";
            sql += "and SQKDRQ>=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
            parms.add(fromDate);
        }
        if (!toDate.equals("")) {
            toDate += " 23:59:59";
            sql += "and SQKDRQ<=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
            parms.add(toDate);
        }
        patientRequestInfoList = hisJdbcTemplate.query(sql, parms.toArray(),
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
