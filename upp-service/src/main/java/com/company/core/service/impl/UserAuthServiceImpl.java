package com.company.core.service.impl;

import com.company.core.constant.SystemConstant;
import com.company.core.entity.SysFunctionDAO;
import com.company.core.entity.SysRoleDAO;
import com.company.core.entity.SysUsrDAO;
import com.company.core.mapper.UserAuthMapper;
import com.company.core.service.IFunctionService;
import com.company.core.service.IRoleService;
import com.company.core.service.IUserService;
import com.company.core.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    UserAuthMapper userAuthMapper;

    @Autowired
    IUserService userService;

    @Autowired
    IFunctionService functionService;

    @Autowired
    IRoleService roleService;

    @Override
    public List<SysFunctionDAO> getAuthByUserId(BigDecimal userId) {
        SysUsrDAO user = (SysUsrDAO) userService.searchById(userId);
        if (SystemConstant.ROOT.equals(user.getName())) {
            return functionService.search(null);
        } else {
            return userAuthMapper.getAuthByUserId(userId);
        }
    }

    @Override
    public List<SysRoleDAO> getRoleByUserId(BigDecimal userId) {
        SysUsrDAO user = (SysUsrDAO) userService.searchById(userId);
        if (SystemConstant.ROOT.equals(user.getName())) {
            return roleService.search(null);
        } else {
            return userAuthMapper.getRoleByUserId(userId);
        }
    }
}
