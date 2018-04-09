package com.company.core.service;

import com.company.core.bo.MenuBO;
import com.company.core.vo.FunctionTree;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by APPLE on 16/1/12.
 */
public interface MenuService {
    List<MenuBO> getAllEnabledMenuByUserId(BigDecimal userid);

    List<FunctionTree> getAllFunctionByRoleId(BigDecimal roleId);
}
