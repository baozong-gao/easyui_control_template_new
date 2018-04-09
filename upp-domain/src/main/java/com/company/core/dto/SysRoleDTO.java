package com.company.core.dto;

import com.company.core.Enum.validator.StringByteSize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: gaobaozong
 * @Description: 用于插入，更新
 * @Date: Created in 2017/12/19 - 10:46
 * @Version: V1.0-SNAPSHOT
 */
@Data
public class SysRoleDTO {

    private BigDecimal id;

    @NotNull(message = "角色名称不能为空")
    @StringByteSize(max = 50, message = "角色名称长度超限")
    private String name;

    private String status;

    @StringByteSize(max = 255, message = "角色备注长度超限")
    private String remark;

    private List<BigDecimal> funcs;
}
