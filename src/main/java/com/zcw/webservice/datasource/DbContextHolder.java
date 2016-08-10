package com.zcw.webservice.datasource;

/**
 * Title: .IntelliJ IDEA
 * Description:
 *
 * @Author:zhou
 * @Date:2016/8/2 9:55
 * @Version:
 */
public class DbContextHolder {
    public static final String HIS = "dataSourceLis";
    public static final String LIS = "dataSourceHis";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDBType() {
        return contextHolder.get();
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}
