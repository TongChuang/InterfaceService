package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.his.AccountItem;
import com.zcw.webservice.model.his.Department;
import com.zcw.webservice.model.his.Patient;
import com.zcw.webservice.model.his.Ward;
import com.zcw.webservice.model.vo.ReturnMsg;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "select * from V_HSBBI_RECORDHOME where BRJZHM ='" + patientCode + "'";
        patientList = hisJdbcTemplate.query(sql,
                new RowMapper<Patient>() {
                    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Patient patient = new Patient();
                        patient.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        patient.setChargeType(Util.null2String(rs.getString("BRZYID")));
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
        String sql = "select * from V_HSBCI_TREATINFO where BRJZHM ='" + patientCode + "'";
        patientList = hisJdbcTemplate.query(sql,
                new RowMapper<Patient>() {
                    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Patient patient = new Patient();
                        patient.setPatientCode(Util.null2String(rs.getString("BRJZHM")));
                        patient.setChargeType(Util.null2String(rs.getString("BRJZXH")));
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
        final String hisUserID = lisJdbcTemplate.queryForObject("select his_id from xt_user where logid=?", new Object[]{accountItem.getOperatorNo()}, String.class);
        if (hisUserID.equals("")) {
            msg.setState(0);
            msg.setMessage("HIS用户不存在，请检查。");
            return msg;
        }

        String sql = "select JZJLID from SEQ_II_INPATICHARGE_JZJLID";
        final Long seqId = hisJdbcTemplate.queryForObject(sql, Long.class);
        sql = "insert into II_INPATICHARGE(JZJLID,BRZYID,YPZLPB,FYXMID,YPCDID,FYTJID," +
                "FYFSRQ,FYFSSL,KDYSID,KDKSID,ZXYHID,ZXKSID,CZYHID)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            hisJdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, seqId);                                   //记账记录序号
                    ps.setObject(2, accountItem.getPatientCode());          //病人住院序号
                    // ps.setObject(3, accountItem.pa());                   //组织机构代码
                    ps.setLong(3, 2);                                       //药品诊疗判别 1 药品 2 诊疗
                    ps.setString(4, accountItem.getFeeItemCode());          //费用项目序号 代码11266
                    //ps.setObject(6, accountItem.getAge());                //药品产地序号 诊疗不需要，药品需传入
                    ps.setLong(5, 14);                                      //费用途径序号 12 用血 14 LIS 15 物资
                    ps.setString(6, accountItem.getDateTime());             //费用发生日期 日期 yyyy-mm-dd hh24:mi:ss
                    ps.setObject(7, accountItem.getQuantity());             //费用发生数量
                    ps.setObject(8, hisUserID);                             //开单医生序号
                    ps.setObject(9, 21);                                    //开单科室序号
                    ps.setObject(10, hisUserID);                            //执行用户序号
                    ps.setObject(11, 21);                                   //执行科室序号
                    ps.setObject(12, hisUserID);                            //操作用户序号
                }
            });
        } catch (Exception e) {
            log.error("计费异常", e);
            msg.setState(1);
            msg.setMessage("计费异常:" + e.getMessage());
        }
        return msg;
    }
}
