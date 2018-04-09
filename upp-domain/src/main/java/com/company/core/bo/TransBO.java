package com.company.core.bo;

import com.company.core.Enum.FiledType;
import com.company.core.Enum.UserTypeEnum;
import com.company.core.vo.BaseForm;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: gaobaozong
 * @Description: 用于查询
 * @Date: Created in 2018/3/16 - 13:47
 * @Version: V1.0
 */
@Data
public class TransBO extends BaseForm<TransBO> {

    private String orderDate;

    private String orderId;

    private String startDate;

    private String endDate;

    private String startTime;

    private String endTime;

    private String orderStatus;

    @FiledType
    private BigDecimal instId;

    @FiledType(UserTypeEnum.MER)
    private BigDecimal merchantId;

    @FiledType(UserTypeEnum.AGE)
    private BigDecimal agentId;
}
