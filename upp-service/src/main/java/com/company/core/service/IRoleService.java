package com.company.core.service;

import com.company.core.entity.RLSysUsrRoleDAO;
import com.company.core.sysexception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: gaobaozong
 * @Description: 角色服务接口
 * @Date: Created in 2017/12/15 - 11:00
 * @Version: V1.0-SNAPSHOT
 */
public interface IRoleService<D, B> {

    int count(B form);

    List<D> search(B form);

    int delete(BigDecimal id) throws PersistentException;

    int insert(D role) throws PersistentException;

    D searchById(BigDecimal id);

    int update(D role) throws PersistentException;

    List getFunctionByRole(BigDecimal id);

    int upFunctionOnRole(BigDecimal roleId, List<BigDecimal> funcId);
    int insertRLUserRole(RLSysUsrRoleDAO rlSysUsrRoleDAO);
}
