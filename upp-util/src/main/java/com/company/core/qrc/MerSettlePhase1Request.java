package com.company.core.qrc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: simons.xiao
 * Date: 2018/3/24
 * Time: 下午7:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerSettlePhase1Request implements Serializable{
    private String merchantId;
    private String prodType;
    private String cardNo;
    private String userName;
    private String branchCode;
    private String branchName;
    private String outcomeAmount;
    private String outcomeRemark;
}