package com.company.core.springUtil;

import java.math.BigDecimal;

/**
 * @Author: gaobaozong
 * @Description: 简单货币工具类
 * @Date: Created in 2018/3/30 - 10:44
 * @Version: V1.0
 */
public class MoneyUtil {

    public static String yuan2Cent(BigDecimal yuan){
        return yuan.movePointRight(2).toString();
    }

    public static String yuan2Cent(String yuan){
        return yuan2Cent(new BigDecimal(yuan));
    }

    public static String yuan2Cent(int yuan){
        return yuan2Cent(new BigDecimal(yuan));
    }

    public static String cent2Yuan(BigDecimal cent){
        return cent.movePointLeft(2).toString();
    }

    public static String cent2Yuan(String cent) {
        return cent2Yuan(new BigDecimal(cent));
    }

    public static String cent2Yuan(long cent) {
        return cent2Yuan(new BigDecimal(cent));
    }

    public static String cent2Yuan(int cent) {
        return cent2Yuan(new BigDecimal(cent));
    }

}
