package com.company.core.dto;

import com.company.core.Enum.validator.StringByteSize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: gaobaozong
 * @Description: 用于插入，更新
 * @Date: Created in 2017/12/21 - 14:03
 * @Version: V1.0-SNAPSHOT
 */
@Data
public class UserDTO {
    private BigDecimal id;

    @NotNull(message = "机构不能为空")
    @StringByteSize(max = 32, message = "机构长度超限")
    private String instId;

    @NotNull(message = "类型不能为空")
    @StringByteSize(max = 2, message = "类型长度超限")
    private String userType;

    @NotNull(message = "名称不能为空")
    @StringByteSize(max = 32, message = "名称长度超限")
    private String name;

    private String pwd;

    @StringByteSize(max = 255, message = "备注长度超限")
    private String remark;

    private String userStatus;

    @StringByteSize(max = 50, message = "邮箱长度超限")
    private String email;

    private List<BigDecimal> roles;
}
