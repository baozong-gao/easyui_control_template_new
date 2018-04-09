package com.company.core.dto;

import com.company.core.Enum.validator.StringByteSize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: gaobaozong
 * @Description: 权限 用于插入，更新
 * @Date: Created in 2017/12/15 - 13:26
 * @Version: V1.0-SNAPSHOT
 */
@Data
public class FunctionDTO {
    private BigDecimal id;

    @NotNull(message = "名称不能为空")
    @StringByteSize(max = 32, message = "名称长度超限")
    private String name;

    @NotNull(message = "请选择父级标签")
    private BigDecimal parentId;

    private String grade;

    private String uri;

    @NotNull(message = "标签码不能为空")
    @StringByteSize(max = 255, message = "标签码长度超限")
    private String code;

    @NotNull(message = "请选择状态")
    private String status;

    private BigDecimal orderBy;

    private String logo;

    private String type;
}
