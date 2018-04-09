package com.company.core.constant;

//#===========================
//#=权限常量表                                          =
//#=增加权限时，注意 增加到相应方法上       =
//#=2016-05-06               =
//#===========================
public class ShiroPermissionsConstant {

    // 用户操作
    public static final String USER_ADD       = "user:add";
    public static final String USER_DEL       = "user:del";
    public static final String USER_UP        = "user:up";
    public static final String USER_QUERY     = "user:query";
    public static final String USER_AUTHORITY = "user:authority";

    // 角色操作
    public static final String ROLE_ADD       = "role:add";
    public static final String ROLE_DEL       = "role:del";
    public static final String ROLE_UP        = "role:up";
    public static final String ROLE_QUERY     = "role:query";
    public static final String ROLE_AUTHORITY = "role:authority";

    // 权限操作
    public static final String FUNC_ADD       = "func:add";
    public static final String FUNC_DEL       = "func:del";
    public static final String FUNC_UP        = "func:up";
    public static final String FUNC_QUERY     = "func:query";
    public static final String FUNC_AUTHORITY = "func:authority";

    //公司操作
    public static final String TRANS_QUERY     = "trans:query";

    //公司账户操作
    public static final String MERCHANT_INFO_ADD       = "merchantInfo:add";
    public static final String MERCHANT_INFO_DEL       = "merchantInfo:del";
    public static final String MERCHANT_INFO_UP        = "merchantInfo:up";
    public static final String MERCHANT_INFO_QUERY     = "merchantInfo:query";

    //代理商操作
    public static final String AGENT_INFO_ADD       = "agentInfo:add";
    public static final String AGENT_INFO_DEL       = "agentInfo:del";
    public static final String AGENT_INFO_UP        = "agentInfo:up";
    public static final String AGENT_INFO_QUERY     = "agentInfo:query";
    
    
    //机构
    public static final String INST_INFO_ADD       = "instInfo:add";
    public static final String INST_INFO_DEL       = "instInfo:del";
    public static final String INST_INFO_UP        = "instInfo:up";
    public static final String INST_INFO_QUERY     = "instInfo:query";

    public static final String ACCOUNT_FROZEN_QUERY     = "accountFrozen:query";

    //代理商操作
    public static final String STLM_CARD_ADD       = "stlmCard:add";
    public static final String STLM_CARD_DEL       = "stlmCard:del";
    public static final String STLM_CARD_UP        = "stlmCard:up";
    public static final String STLM_CARD_QUERY     = "stlmCard:query";
    public static final String STLM_CARD_DEFAULT   = "stlmCard:default";
    public static final String STLM_WITHDRAW_PWD   = "stlmCard:withdraw";


    //开户
    public static final String QRC_MER_ADD       = "qrcMer:add";
    public static final String QRC_MER_FEE       = "qrcMer:fee";
    public static final String QRC_MER_QUERY     = "qrcMer:query";
    public static final String QRC_MER_ROUTE     = "qrcMer:route";


    // 操作员操作
    public static final String OPERATOR_ADD       = "operator:add";
    public static final String OPERATOR_DEL       = "operator:del";
    public static final String OPERATOR_UP        = "operator:up";
    public static final String OPERATOR_QUERY     = "operator:query";
    public static final String OPERATOR_AUTHORITY = "operator:authority";

}
