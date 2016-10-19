package com.zcw.webservice.dao;

import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.his.*;
import com.zcw.webservice.model.vo.ReturnMsg;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
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
    public List<Ward> getWardList() throws Exception {
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

        return wardInfoList;
    }

    /**
     * 获取His科室信息
     *
     * @return
     */
    public List<Department> getDepartmentList() throws Exception {
        String sql = "select * from V_HSBHI_DEPTINFO";

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
    public List<Patient> getPatientInfo(String patientType, String patientCode, String patientId) throws Exception {
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
     * 住院病人信息列表
     * @param ward 住院病区
     * @return 返回病人信息
     */
    public List<Patient> getInPatientList(String ward) throws Exception {
        List<Patient> patientList = null;
        String sql = "";
        Object[] parms = null;
        if (!ward.equals("")) {
            sql = "select T1.*,T2.ZZKSMC from V_HSBBI_RECORDHOME t1,V_HSBHI_WARDINFO t2 where t1.BRZYZT=2 and t1.ZYBQID=T2.ZZKSID and ZYBQID=? order by t1.ZYCWHM  ";
            parms = new Object[]{ward};
            patientList = hisJdbcTemplate.query(sql, parms,
                    new RowMapper<Patient>() {
                        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Patient patient = new Patient();
                            patient.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                            patient.setPatientId(Util.null2String(rs.getString("BRZYID")));
                            patient.setName(Util.null2String(rs.getString("BRDAXM")));
                            patient.setSex(Util.null2String(rs.getString("BRDAXB")));
                            patient.setBirthday(Util.null2String(rs.getDate("BRCSRQ")));
                            patient.setAge(Util.null2String(rs.getString("BRJZNL")));
                            patient.setAgeUnit(Util.null2String(rs.getString("BRNLDW")));
                            patient.setDepartment(Util.null2String(rs.getString("RYKSID"))); //入院科室
                            patient.setDoctor(Util.null2String(rs.getString("ZZYSID")));
                            patient.setCompany(Util.null2String(rs.getString("BRDWMC")));
                            patient.setIdCard(Util.null2String(rs.getString("BRSFZH")));
                            patient.setAdmissionDepartment(Util.null2String(rs.getString("RYKSID")));
                            patient.setHospitalWard(Util.null2String(rs.getString("RYBQID")));
                            patient.setInpatientDepartment(Util.null2String(rs.getString("ZYKSID")));
                            patient.setInpatientWardId(Util.null2String(rs.getString("ZYBQID")));
                            patient.setInpatientWard(Util.null2String(rs.getString("ZZKSMC")));
                            patient.setBedno(Util.null2String(rs.getString("ZYCWHM")));
                            patient.setStatus(Util.null2String(rs.getString("BRZYZT")));
                            patient.setPatientType("2");
                            patient.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                            patient.setChargeType(Util.null2String(rs.getString("BRLBID")));
                            patient.setInDateTime(rs.getTimestamp("BRRYRQ"));
                            //patient.setInDateTime(rs.getDate("BRJZRQ"));
                            patient.setPatientPhone(Util.null2String(rs.getString("BRLXDH")));
                            patient.setPatientAddress(Util.null2String(rs.getString("BRJTDZ")));

                            return patient;
                        }
                    });
        }
        return patientList;
    }


    /**
     * 获取住院病人信息
     *
     * @param patientCode
     * @return 返回住院病人信息
     */
    private List<Patient> getInPatientInfo(String patientCode, String patientId) throws Exception {
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
                        patient.setBirthday(Util.null2String(rs.getDate("BRCSRQ")));
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
                        //patient.setInDateTime(rs.getDate("BRJZRQ"));
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

    private List<Patient> getOutPatientInfo(String patientCode, String patientId) throws Exception {
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
                        patient.setBirthday(Util.null2String(rs.getDate("BRCSRQ")));
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
    public ReturnMsg saveBooking(final List<AccountItem> accountItem) throws Exception {
        ReturnMsg msg = new ReturnMsg();
        String userids = "";
        String sql = "";
        //插入收费记录
        sql = "insert into II_INPATICHARGE(JZJLID,BRZYID,YPZLPB,FYXMID,FYTJID," +
                "FYFSRQ,FYFSSL,KDYSID,KDKSID,ZXYHID,ZXKSID,CZYHID,DYJZID)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            this.hisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    int length = accountItem.size();
                    for (AccountItem item : accountItem) {
                        String sql_1 = "select  ETRACKHIS.SEQ_II_INPATICHARGE_JZJLID.Nextval as id from dual";
                        Long seqId = hisJdbcTemplate.queryForObject(sql_1, Long.class);
                        ps.setLong(1, seqId);                     //记账记录序号
                        ps.setObject(2, item.getPatientId());                 //病人就诊序号
                        ps.setLong(3, 2);                                           //药品诊疗判别 1 药品 2 诊疗
                        ps.setString(4, item.getFeeItemCode());              //费用项目序号 代码11266
                        //ps.setObject(6, accountItem.getAge());                    //药品产地序号 诊疗不需要，药品需传入
                        ps.setLong(5, 14);                                          //费用途径序号 12 用血 14 LIS 15 物资
                        ps.setTimestamp(6, new java.sql.Timestamp(item.getDateTime().getTime()));   //费用发生日期 日期 yyyy-mm-dd hh24:mi:ss
                        ps.setInt(7, item.getQuantity());                    //费用发生数量
                        ps.setString(8, item.getBillingDoctorNo());                                 //开单医生序号
                        ps.setString(9, item.getBillingDeptNo());                                      //开单科室序号
                        ps.setString(10, item.getTestDoctorNo());                                //执行用户序号
                        ps.setString(11, item.getTestDoctorDeptNo());                                     //执行科室序号
                        ps.setString(12, item.getOperatorNo());                                //操作用户序号
                        if (item.getQuantity() < 0) {
                            ps.setLong(13, item.getAccountId());                                //操作用户序号
                        } else {
                            ps.setObject(13, null);
                            item.setAccountId(seqId);
                        }
                        ps.addBatch();
                    }
                    Object o = ps.executeBatch();
                    return o;
                }
            });
            return new ReturnMsg(1, accountItem);
        } catch (Exception e) {
            e.printStackTrace();
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
        String sql = "{call PKG_Etrack_Interface.Prc_RequestUpdate(?,?,?,?,?,?,?,?,?)}";
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
                        cs.setString(7, Util.null2String(param.getExpand()));
                        cs.registerOutParameter(8, OracleTypes.NUMBER);
                        cs.registerOutParameter(9, OracleTypes.VARCHAR);
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

    /**
     * 住院病人申请信息
     *
     * @param requestType
     * @param executeStatus
     * @param ward
     * @return
     * @throws Exception
     */
    public List<PatientRequestInfo> getInPatientRequestInfo(int requestType, int executeStatus, String ward, String bedNo, String patientId) throws Exception {
        List<PatientRequestInfo> patientRequestInfoList = null;
        String sql = "select * from V_HSBDI_REQUESTINFO where SFDYPB <> 1 AND BRSQLX =?  and DQBQID=?  and SQZTBZ=? ";
        List<Object> parms = new ArrayList<Object>();
        parms.add(requestType);
        parms.add(ward);
        parms.add(executeStatus);
        //床位号
        if (bedNo != null && !bedNo.isEmpty()) {
            sql += " and BQCWHM = ? ";
            parms.add(bedNo);
        }
        //病人就诊ID
        if (patientId != null && !patientId.isEmpty()) {
            sql += " and BRZYID = ? ";
            parms.add(patientId);
        }
        sql += " order by BQCWHM,sqkdrq ";
        patientRequestInfoList = hisJdbcTemplate.query(sql, parms.toArray(),
                new RowMapper<PatientRequestInfo>() {
                    public PatientRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientRequestInfo info = new PatientRequestInfo();
                        info.setRequestId(Util.getLongValue(rs.getString("SQJLID")));
                        info.setRequestDetailId(Util.getLongValue(rs.getString("SQMXID")));
                        info.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        info.setPatientId(Util.null2String(rs.getString("BRZYID")));
                        info.setPatientRequestCode(Util.null2String(rs.getString("BRSQHM")));
                        info.setName(Util.null2String(rs.getString("BRDAXM")));
                        int sex = Util.getIntValue(rs.getString("BRDAXB"), 3);
                        sex = (sex == 0 ? 3 : sex);
                        info.setSex("" + sex);
                        info.setBirthday(Util.null2String(rs.getDate("BRCSRQ")));
                        info.setDepartment(Util.null2String(rs.getString("DQKSID")));
                        info.setWard(Util.null2String(rs.getString("DQBQID")));
                        info.setWardName(Util.null2String(rs.getString("DQBQMC")));
                        info.setBedno(Util.null2String(rs.getString("BQCWHM")));
                        info.setDiagnose(Util.null2String(rs.getString("JBZDMC")));
                        info.setRequestType(Util.null2String(rs.getString("BRSQLX")));
                        info.setRequestItemType(Util.null2String(rs.getString("SQXMLX")));
                        info.setRequestDoctor(Util.null2String(rs.getString("KDYSID")));
                        info.setRequestDoctorName(Util.null2String(rs.getString("KDYSXM")));
                        info.setRequestDepartment(Util.null2String(rs.getString("KDKSID")));
                        info.setRequestDepartmentName(Util.null2String(rs.getString("KDKSMC")));
                        info.setRequestDateTime(rs.getTimestamp("SQKDRQ"));
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
                        info.setSampleType(Util.null2String(rs.getString("YBLXMC")));
                        info.setAge(Util.null2String(rs.getString("BRJZNL")));
                        info.setAgeUnit(Util.null2String(rs.getString("BRNLDW")));
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
    public List<PatientRequestInfo> getOutPatientRequestInfo(int requestType, String requestId, String requestDetailId, String testItemId, int executeStatus, String patientId, String fromDate, String toDate) throws Exception {
        List<PatientRequestInfo> patientRequestInfoList = null;
        String sql = "select * from V_HSBCI_REQUESTINFO where BRSQLX =? and SQZTBZ=? ";
        List<Object> parms = new ArrayList<Object>();
        parms.add(requestType);
        if (requestType == 2) {
            executeStatus = 0;
        }
        parms.add(executeStatus);
        if (patientId != null && !patientId.isEmpty()) {
            sql += " and BRJZHM=?";
            parms.add(patientId);
        }
        if (!fromDate.isEmpty()) {
            fromDate += " 00:00:00";
            sql += " and SQKDRQ>=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
            parms.add(fromDate);
        }
        if (!toDate.isEmpty()) {
            toDate += " 23:59:59";
            sql += "and SQKDRQ<=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
            parms.add(toDate);
        }
        if (!requestId.isEmpty()) {
            sql += "and SQJLID=?";
            parms.add(requestId);
        }
        if (!testItemId.isEmpty()) {
            sql += "and JCXMID=?";
            parms.add(testItemId);
        }
        if (!requestDetailId.isEmpty()) {
            sql += "and SQMXID in (" + requestDetailId + ")";
        }
        
        patientRequestInfoList = hisJdbcTemplate.query(sql, parms.toArray(),
                new RowMapper<PatientRequestInfo>() {
                    public PatientRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientRequestInfo info = new PatientRequestInfo();
                        info.setRequestId(Util.getLongValue(rs.getString("SQJLID")));
                        info.setRequestDetailId(Util.getLongValue(rs.getString("SQMXID")));
                        info.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        info.setPatientId(Util.null2String(rs.getString("BRJZXH")));
                        info.setPatientRequestCode(Util.null2String(rs.getString("BRSQHM")));
                        info.setName(Util.null2String(rs.getString("BRDAXM")));
                        info.setSex("" + Util.getIntValue(rs.getString("BRDAXB"), 3));
                        info.setBirthday(Util.null2String(rs.getDate("BRCSRQ")));
                        //info.setDepartment(Util.null2String(rs.getString("DQKSID")));
                        info.setDiagnose(Util.null2String(rs.getString("JBZDMC")));
                        info.setRequestType(Util.null2String(rs.getString("BRSQLX")));
                        //info.setRequestItemType(Util.null2String(rs.getString("SQXMLX")));
                        info.setRequestDoctor(Util.null2String(rs.getString("KDYSID")));
                        info.setRequestDoctorName(Util.null2String(rs.getString("KDYSXM")));
                        info.setRequestDepartment(Util.null2String(rs.getString("KDKSID")));
                        info.setRequestDepartmentName(Util.null2String(rs.getString("KDKSMC")));
                        info.setRequestDateTime(rs.getTimestamp("SQKDRQ"));
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
                        info.setSampleType(Util.null2String(rs.getString("YBLXMC")));
                        info.setAge(Util.null2String(rs.getString("BRJZNL")));
                        info.setAgeUnit(Util.null2String(rs.getString("BRNLDW")));
                        return info;
                    }
                });
        return patientRequestInfoList;
    }

    public List<PatientRequestInfo> getExaminationRequestInfo(String barcode, String patientId, String fromDate, String toDate)throws Exception {
        List<PatientRequestInfo> patientRequestInfoList = null;
        String sql = "select * from V_HSBTJ_REQUESTINFO where 1=1 ";

        List<Object> parms = new ArrayList<Object>();
        if (barcode != null && !barcode.isEmpty()) {
            sql += " and BRSQHM=?";
            parms.add(barcode);
        }
        if (patientId != null && !patientId.isEmpty()) {
            sql += " and TIJIANBM=?";
            parms.add(patientId);
        }
        if (!fromDate.isEmpty()) {
            fromDate += " 00:00:00";
            sql += " and TIJIANRQ >= to_date(?,'yyyy-MM-dd hh24:mi:ss')";
            parms.add(fromDate);
        }
        if (!toDate.isEmpty()) {
            toDate += " 23:59:59";
            sql += "and TIJIANRQ <= to_date(?,'yyyy-MM-dd hh24:mi:ss')";
            parms.add(toDate);
        }

        patientRequestInfoList = hisJdbcTemplate.query(sql, parms.toArray(),
                new RowMapper<PatientRequestInfo>() {
                    public PatientRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        PatientRequestInfo info = new PatientRequestInfo();
                        info.setRequestId(Util.getLongValue(rs.getString("SQJLID")));
                        info.setRequestDetailId(Util.getLongValue(rs.getString("SQMXID")));
                        info.setPatientCode(Util.null2String(rs.getString("TIJIANBM")));
                        info.setPatientId(Util.null2String(rs.getString("TIJIANBM")));
                        info.setPatientRequestCode(Util.null2String(rs.getString("BRSQHM")));
                        info.setName(Util.null2String(rs.getString("XINGMING")));
                        info.setSex("" + Util.getIntValue(rs.getString("XINGBIE"), 3));
                        info.setBirthday(Util.null2String(rs.getDate("CHUSHENGRQ")));
                        //info.setDepartment(Util.null2String(rs.getString("DQKSID")));
                        //info.setDiagnose(Util.null2String(rs.getString("JBZDMC")));
                        info.setRequestType("4");   //体检
                        //info.setRequestItemType(Util.null2String(rs.getString("SQXMLX")));
                        info.setRequestDoctor(Util.null2String(rs.getString("SONGJIANYSGH")));
                        info.setRequestDoctorName(Util.null2String(rs.getString("SONGJIANYSGH")));
                        info.setRequestDepartment(Util.null2String(rs.getString("SONGJIANKSDM")));
                        info.setRequestDepartmentName(Util.null2String(rs.getString("SONGJIANKSMC")));
                        info.setRequestDateTime(rs.getTimestamp("TIJIANRQ"));
                        info.setItemCode(Util.null2String(rs.getString("YLXH")));
                        info.setItemName(Util.null2String(rs.getString("ZUHEXMMC")));
                        //info.setItemPrintName(Util.null2String(rs.getString("XMDYMC")));
                        info.setQuantity(1);
                        info.setStatus(Util.getIntValue(rs.getString("TIJIANDZT")));
//                        info.setTestDept(Util.null2String(rs.getString("ZXKSID")));
//                        info.setEmergency(Util.getIntValue(rs.getString("SFJZPB")));
                        info.setAmount((rs.getFloat("DANJIA")));
                        //info.setTestPart(Util.null2String(rs.getString("ZLBWMC")));
                        //info.setPatientFileCode(Util.null2String(rs.getString("BRDABH")));
                        //info.setSampleType(Util.null2String(rs.getString("YBLXMC")));
//                        info.setAge(Util.null2String(rs.getString("BRJZNL")));
//                        info.setAgeUnit(Util.null2String(rs.getString("BRNLDW")));
                        return info;
                    }
                });


        return patientRequestInfoList;
    }
    @Transactional(rollbackFor = Exception.class)
    public ReturnMsg saveHisResult(HisTestInfo info) throws Exception {
        ReturnMsg msg = new ReturnMsg();
        final HisSampleInfo sampleInfo = info.getSampleInfo();

        //删除样本信息
        String sql = "delete from di_labsampleinfo where ZZJGDM=1001 AND BRYZID=? and SJBRID=? ";
        hisJdbcTemplate.update(sql, new Object[] { sampleInfo.getBarCode(),sampleInfo.getPatientId()});

        //删除结果信息
        sql = "delete from di_labtestresult where ZZJGDM=1001 AND JCYBID=? ";
        hisJdbcTemplate.update(sql, new Object[] { sampleInfo.getBarCode()});

        //插入样本信息
        sql = "insert into di_labsampleinfo(BRYZID,JCYBID,ZZJGDM,SJBRLX,SJBRID," +
                "BRDAID,BRJZHM,SJBRXM,SJBRXB,SJBRNL,BRNLDW,SJYEPB,SJBRCH,LCZDDM,LCZDMC" +
                ",SJCJBW,SLZQID,YBSJSJ,KDYSID,KDYSXM,KDKSID,KDKSMC,YBJSSJ,ZXRYID,ZXRYXM,ZXKSID " +
                ",ZXKSMC,YBZXSJ,SHRYID,SHRYXM,YBSHSJ,YBSHBZ,YBLXID,YBLXMC,YBCZZT,SJFYHJ" +
                ",SJSFZT,JSJLID,YBJGSJ,YBJGZT,SFDYPB,SFJZPB,JCMDDM,JCMDMC,JGLJDZ,JGBZSM" +
                ",BRDABH) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        hisJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, Util.null2String(sampleInfo.getBarCode()));           //条码号
                ps.setString(2, Util.null2String(sampleInfo.getBarCode()));          //样本ID
                ps.setString(3, Util.null2String(sampleInfo.getOrganizationId()));          //组织机构ID
                ps.setInt(4, sampleInfo.getPatientType());          //病人类型
                ps.setString(5, Util.null2String(sampleInfo.getPatientId()));          //病人ID
                ps.setString(6, Util.null2String(sampleInfo.getPatientCode()));          //病人档案ID
                ps.setString(7, Util.null2String(sampleInfo.getPatientNo()));          //病人就诊号码：住院号、门诊号
                ps.setString(8, Util.null2String(sampleInfo.getPatientName()));          //病人姓名
                ps.setInt(9, sampleInfo.getSex());
                ps.setInt(10, sampleInfo.getAge());
                ps.setString(11, Util.null2String(sampleInfo.getAgeUnit()));
                ps.setInt(12, sampleInfo.getIsBaby());
                ps.setString(13, Util.null2String(sampleInfo.getBedNo()));       //床号
                ps.setString(14, Util.null2String(sampleInfo.getDiagnosisId())); //临床诊断ID
                ps.setString(15, Util.null2String(sampleInfo.getDiagnosis()));   //临床诊断
                ps.setString(16, Util.null2String(sampleInfo.getPart()));        //检测部位
                ps.setString(17, Util.null2String(sampleInfo.getCycleId()));     //生理周期ID
                ps.setTimestamp(18, new java.sql.Timestamp(sampleInfo.getExecuteTime().getTime()));      //送检时间
                ps.setString(19, Util.null2String(sampleInfo.getRequesterId()));     //开单医生ID
                ps.setString(20, Util.null2String(sampleInfo.getRequesterName()));   //开单医生姓名
                ps.setString(21, Util.null2String(sampleInfo.getDepartmentId()));    //开单科室ID
                ps.setString(22, Util.null2String(sampleInfo.getDepartmentName()));  //开单科室名称
                ps.setTimestamp(23, new java.sql.Timestamp(sampleInfo.getReceiveTime().getTime()));      //样本接收时间
                ps.setString(24, Util.null2String(sampleInfo.getTesterId()));        //执行人员ID
                ps.setString(25, Util.null2String(sampleInfo.getTesterName()));      //执行人员名称
                ps.setString(26, Util.null2String(sampleInfo.getTestDepartmentId()));      //执行科室ID
                ps.setString(27, Util.null2String(sampleInfo.getTestDepartmentName()));      //执行科室名称
                ps.setTimestamp(28, new java.sql.Timestamp(sampleInfo.getTestTime().getTime()));      //样本执行时间
                ps.setString(29, Util.null2String(sampleInfo.getAuditerId()));        //审核人员ID
                ps.setString(30, Util.null2String(sampleInfo.getAuditerName()));      //审核人员名称
                ps.setTimestamp(31, new java.sql.Timestamp(sampleInfo.getAuditTime().getTime()));      //样本审核时间
                ps.setString(32, Util.null2String(sampleInfo.getAuditNote()));      //审核备注
                ps.setString(33, "");      //样本类型ID
                ps.setString(34, Util.null2String(sampleInfo.getSampleTypeName()));      //样本类型名称
                ps.setInt(35, 0);     //样本操作状态
                ps.setInt(36, 0);     //送检费用合计
                ps.setInt(37, 0);     //送检收费状态
                ps.setString(38, "");      //接收记录ID
                ps.setTimestamp(39, new java.sql.Timestamp(sampleInfo.getSampleResultTime().getTime()));      //样本结果时间
                ps.setInt(40, sampleInfo.getSampleResultStatus());      //样本结果状态
                ps.setInt(41, sampleInfo.getIsPrint());      //是否打印判断
                ps.setInt(42, sampleInfo.getIsEmergency());      //是否急诊判别
                ps.setString(43, Util.null2String(sampleInfo.getTestId()));      //检测目的ID
                ps.setString(44, Util.null2String(sampleInfo.getTestName()));      //检测目的名称
                ps.setString(45, Util.null2String(sampleInfo.getReportUrl()));      //报告单路径
                ps.setString(46, "");      //结果备注说明
                ps.setString(47, Util.null2String(sampleInfo.getPatientNo()));      //病人档案编号
            }
        });

        //保存结果信息
        final List<HisTestResult> results = info.getTestResultList();
        sql = "insert into di_labtestresult(JCYBID,ZZJGDM,JCXMID,JCXMYW,JCXMZW,YBLXID,YBLXMC" +
                ",YBCZZT,YBJCJG,JCJGBZ,JCJGTS,XMJLDW,XMJGXX,XMJGSX,XMJGFW,JGCZSJ" +
                ",JCJGCX,SYCZFF) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.hisJdbcTemplate.execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                int length = results.size();
                for (HisTestResult result : results) {
                    ps.setString(1, Util.null2String(sampleInfo.getBarCode()));               //样本号
                    ps.setString(2, Util.null2String(sampleInfo.getOrganizationId()));        //结果类型
                    ps.setString(3, Util.null2String(result.getTestItemId()));               //结果类型序号
                    ps.setString(4, Util.null2String(result.getTestItemName_EN()));             //结果
                    ps.setString(5, Util.null2String(result.getTestItemName_CN()));
                    ps.setString(6, "");
                    ps.setString(7, "");
                    ps.setInt(8, 0);        //样本操作状态
                    ps.setString(9, Util.null2String(result.getTestResult()));
                    ps.setString(10, Util.null2String(result.getResultFlag()));
                    ps.setString(11, Util.null2String(result.getResultHint()));
                    ps.setString(12, Util.null2String(result.getUnit()));
                    ps.setString(13, Util.null2String(result.getReferenceLo()));
                    ps.setString(14, Util.null2String(result.getReferenceHi()));
                    ps.setString(15, Util.null2String(result.getReference()));
                    ps.setTimestamp(16, new java.sql.Timestamp(result.getResultTime().getTime()));      //结果操作时间
                    ps.setInt(17, result.getOrder());        //结果次序
                    ps.setString(18, Util.null2String(result.getMethod()));          //操作方法
                    ps.addBatch();
                }
                Object obj = ps.executeBatch();
                return obj;
            }
        });
        //异常测试
        /*if (1 == 1) {
            throw new Exception("错误！！！");
        }*/
        return new ReturnMsg(1, "保存成功");
    }

    /**
     * 获取His用户信息
     *
     * @return
     */
    public List<HospitalUser> getHospitalUserList() throws Exception {
        String sql = "select * from V_HSBHI_WORKERINFO";

        Object[] params = new Object[]{};
        List<HospitalUser> hospitalUserList = null;
        hospitalUserList = hisJdbcTemplate.query(sql,
                new RowMapper<HospitalUser>() {
                    public HospitalUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                        HospitalUser hospitalUser = new HospitalUser();
                        hospitalUser.setId(Util.null2String(rs.getString("ZZZGID")));
                        hospitalUser.setWorkId(Util.null2String(rs.getString("ZZZGGH")));
                        hospitalUser.setName(Util.null2String(rs.getString("ZZZGXM")));
                        hospitalUser.setPhone(Util.null2String(rs.getString("ZZZGXM")));
                        return hospitalUser;
                    }
                });

        return hospitalUserList;
    }
}
