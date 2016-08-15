package com.zcw.webservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by zcw on 2016/8/11.
 */
public class BaseDao {
    @Autowired
    protected JdbcTemplate hisJdbcTemplate;        //HIS系统连接池

    @Autowired
    protected JdbcTemplate lisJdbcTemplate;        //LIS系统连接池

}
