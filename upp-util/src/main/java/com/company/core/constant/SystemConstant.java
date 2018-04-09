package com.company.core.constant;

public class SystemConstant extends ConfigurableContants {

    static {
        init("/system.properties");
    }

    // shiro cache config
    public static String IP_SERVICE = getProperty("redis.ip");
    public static String PORT_SERVICE = getProperty("redis.port");

    //root 绕过权限
    public static String ROOT = getProperty("root.name");
    public static final String USER_SESSION_KEY = "User_Session_key";

    public static final String EMAIL_HOST_NAME = getProperty("email.hostName");
    public static final String EMAIL_CHARSET = getProperty("email.charset");
    public static final String EMAIL_SANDER = getProperty("email.sender");
    public static final String EMAIL_USER_NAME = getProperty("email.userName");
    public static final String EMAIL_USER_PASSWORD = getProperty("email.userPassword");


    //api 配置
    public static String API_ROOT = getProperty("api.root.path", "/api");
    public static int API_PORT = getIntProperty("api.port", 8881);
    public static int API_IO_COUNT = getIntProperty("api.io.size", Runtime.getRuntime().availableProcessors() * 2);
    public static int API_WORK_COUNT = getIntProperty("api.work.size", Runtime.getRuntime().availableProcessors() * 4);
    public static int API_REQUEST_COUNT = getIntProperty("api.request.size", 1024 * 1024 * 10);


    //商户提现配置
    public static String PAY_URL = getProperty("pay.url", "http://120.78.189.242:9002/uss/mer/tixian/phase1/v0");
    public static String PAY_URL1 = getProperty("pay.url1", "http://120.78.189.242:9002/uss/mer/tixian/phase2/v0");
    public static String PAY_PLATFORM = getProperty("pay.platform", "10000000");
    public static String PAY_SECRET = getProperty("pay.secret", "0123456789ABCDEF");

    //账户冻结解冻
    public static String BALANCE_OFF_URL = getProperty("balance.off.url", "http://120.78.189.242:9001/merchant/account/debit/freeze/v0");
    public static String BALANCE_ON_URL = getProperty("balance.on.url", "http://120.78.189.242:9001/merchant/account/debit/unfreeze/v0");

}
