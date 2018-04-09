package com.company.core.qrc;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: simons.xiao
 * Date: 2018/3/28
 * Time: 下午1:33
 */
@Data
public class UnFreezeMerBalanceRequest {
    private String merchantId;
    private String prodType;
    private String unFreezeAmount;
}