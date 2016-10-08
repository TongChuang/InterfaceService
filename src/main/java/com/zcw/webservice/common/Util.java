package com.zcw.webservice.common;

import java.text.DecimalFormat;
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
     *
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

    public static java.sql.Date toSqlDate(String dateTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //格式化时间
            Date sDate = sdf.parse(dateTime); //格式化startDate 开始时间为 Date类型
            java.sql.Date staDate = new java.sql.Date(sDate.getTime()); //开始时间 转换成sqldate类型
            return staDate;
        } catch (Exception e) {
        }
        return null;
    }

    public static java.util.Date toDate(String dateTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //格式化时间
            Date sDate = sdf.parse(dateTime); //格式化startDate 开始时间为 Date类型
            return sDate;
        } catch (Exception e) {
        }
        return null;
    }

    public static String[] getRefernce(String reference) {
        String[]  str = new String[]{};
        try {
            if (reference == null || reference.isEmpty()) {
                str[0] = "";
                str[1] = "";
            } else {
                if(reference.indexOf("-") >= 0) {
                    String[] refArr = reference.split("-");
                    if(refArr.length == 2) {
                        str[0] =refArr[0];
                        str[1] =refArr[1];
                    } else if (refArr.length == 3) {
                        str[0] ="-" + refArr[1];
                        str[1] =refArr[2];
                    } else {
                        str[0] =reference;
                        str[1] ="";
                    }
                } else if (reference.indexOf("<") == 0) {
                    int round = 0;
                    if(reference.substring(1).indexOf(".") > 0) {
                        round = reference.substring(1).split("[.]")[1].length();
                    }
                    StringBuilder sb = new StringBuilder("#0.");
                    for(int i = 0; i < round; i++) {
                        sb.append("0");
                    }
                    DecimalFormat df  = new DecimalFormat(sb.toString());
                    str[0] =df.format(0d);
                    str[1] = reference.substring(1);
                } else {
                    str[0] =reference;
                    str[1] = "";
                }
            }
        } catch (Exception e) {
            str[0] = "";
            str[1] = "";
            e.printStackTrace();
        }
        return  str;
    }

}