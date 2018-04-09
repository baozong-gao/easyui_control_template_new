package com.company.core.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: gaobaozong
 * @Description: 用于展示
 * @Date: Created in 2017/12/21 - 14:03
 * @Version: V1.0-SNAPSHOT
 */
@Data
public class UserVO {
    private BigDecimal id;

    private BigDecimal instId;

    private String userType;

    private String name;

    private String pwd;

    private String remark;

    private String userStatus;

    private String email;

}
