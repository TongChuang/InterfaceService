package com.zcw.webservice.dao;

import com.alibaba.fastjson.JSON;
import com.zcw.webservice.common.Util;
import com.zcw.webservice.model.his.Department;
import com.zcw.webservice.model.his.Patient;
import com.zcw.webservice.model.his.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
public class HisInfoDao {

    @Autowired
    private JdbcTemplate hisJdbcTemplate;        //LIS系统连接池

    /**
     * 获取His病区信息
     * @return
     */
    public String getWardList() throws Exception{
        String sql ="select * from V_HSBHI_WARDINFO";
        List<Map<String, Object>> list = hisJdbcTemplate.queryForList(sql);
        Object[] params = new Object[] { };
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
     * @return
     */
    public String getDepartmentList() throws Exception{
        String sql ="select * from V_HSBHI_DEPTINFO";
        List<Map<String, Object>> list = hisJdbcTemplate.queryForList(sql);
        Object[] params = new Object[] {};
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

        return JSON.toJSONString(departmentList);
    }

    /**
     * 病人信息
     * @param patientType       病人类别:住院、门诊..
     * @param patientCode       住院、门诊号
     * @return 返回病人信息
     */
    public String getPatientInfo(String patientType,String patientCode){
        if(patientType.equals("1")){
            //住院病人信息
            return getInPatientInfo(patientCode);
        }
        if(patientType.equals("2")){
            //门诊病人信息
            return getOutPatientInfo(patientCode);
        }
        return "";
    }

    /**
     * 获取住院病人信息
     * @param patientCode
     * @return 返回住院病人信息
     */
    private String getInPatientInfo(String patientCode){
        List<Patient> patientList = null;
        String sql = "select * from V_HSBBI_RECORDHOME where BRJZHM ='" +patientCode +"'";
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

        return JSON.toJSONString(patientList);
    }

    /**
     * 获取门诊病人信息
     * @param patientCode
     * @return 返回门诊病人信息
     */
    private String getOutPatientInfo(String patientCode){

        List<Patient> patientList = null;
        String sql = "select * from V_HSBCI_TREATINFO where BRJZHM ='" +patientCode +"'";
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

        return JSON.toJSONString(patientList);
    }
}
