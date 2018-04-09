package com.company.core.service;

import com.company.core.entity.SysFunctionDAO;
import com.company.core.entity.SysRoleDAO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by APPLE on 16/1/13.
 */
public interface UserAuthService {

    List<SysFunctionDAO> getAuthByUserId(BigDecimal userId);

    List<SysRoleDAO> getRoleByUserId(BigDecimal userId);

}
