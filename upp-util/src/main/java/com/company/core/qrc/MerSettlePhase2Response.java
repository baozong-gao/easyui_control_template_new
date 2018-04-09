package com.company.core.qrc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: simons.xiao
 * Date: 2018/3/25
 * Time: 下午9:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerSettlePhase2Response implements Serializable{
    private String merchantId;
    private String outcomeOrderId;
    private String settleStatus;
    private String respCode;
    private String respMessage;
}