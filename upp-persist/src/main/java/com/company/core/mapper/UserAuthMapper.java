package com.company.core.mapper;

import com.company.core.entity.SysFunctionDAO;
import com.company.core.entity.SysRoleDAO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface UserAuthMapper {

    List<SysFunctionDAO> getAuthByUserId(@Param("userId") BigDecimal userId);

    List<SysRoleDAO> getRoleByUserId(@Param("userId") BigDecimal userId);
}
