package com.zcw.webservice.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: Util
 * Description:工具类
 *
 * @Author:zhou
 * @Date:2016/8/2 16:42
 * @Version:
 */
public final class Util {
    private Util() {
    }

    /**
     * null2String
     * @param s
     * @return
     */
    public static String null2String(String s) {
        return s == null ? "" : s;

    }
    public static String null2String(Object s) {
        return s == null ? "" : s.toString();

    }
    public static int getIntValue(String v) {
        return getIntValue(v, -1);
    }

    public static int getIntValue(String v, int def) {
        try {
            return Integer.parseInt(v);
        } catch (Exception ex) {
            return def;
        }
    }
    public static Long getLongValue(String v) {
        return getLongValue(v, -1l);
    }

    public static Long getLongValue(String v, Long def) {
        try {
            return Long.parseLong(v);
        } catch (Exception ex) {
            return def;
        }
    }

    public static java.sql.Date toSqlDate  (String dateTime){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //格式化时间
            Date sDate= sdf.parse(dateTime); //格式化startDate 开始时间为 Date类型
            java.sql.Date staDate = new java.sql.Date(sDate.getTime()); //开始时间 转换成sqldate类型
            return staDate;
        }catch (Exception e){
        }
        return null;
    }

    public static java.util.Date toDate (String dateTime){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //格式化时间
            Date sDate= sdf.parse(dateTime); //格式化startDate 开始时间为 Date类型
            return sDate;
        }catch (Exception e){
        }
        return null;
    }
}
