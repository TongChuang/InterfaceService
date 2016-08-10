package com.zcw.webservice.common;

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
}
