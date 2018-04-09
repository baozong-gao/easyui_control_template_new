package com.company.core.enums;

/**
 * Created by lijinzhu on 2018/3/16.
 */
public enum MerchantAttchmentTypeEnum {

    SFZZ,//身份证正面
    SFZF,//身份证反面
    YYZZZ,//营业执照正面
    YYZZF,//营业执照反面
    MTZ,//门头照
    SCZ,//手持照
    SYT,//收银台
    ;

    private String typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

}
