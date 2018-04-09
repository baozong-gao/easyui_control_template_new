package com.company.core.service;

import com.company.core.entity.SysUsrDAO;
import com.company.core.sysexception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: gaobaozong
 * @Description: 用户服务接口
 * @Date: Created in 2017/12/21 - 14:06
 * @Version: V1.0-SNAPSHOT
 */
public interface IUserService<D, B> {
    int count(B form);

    List<D> search(B form);

    int delete(BigDecimal id) throws PersistentException;

    int insert(D role) throws PersistentException;
    int insertUser(D role) throws PersistentException;
    int insertOperator(SysUsrDAO sysUsrDAO) throws PersistentException;

    D searchById(BigDecimal id);

    int update(D role) throws PersistentException;

    List getRoleByUserId(BigDecimal id);

    void upRoleOnUser(BigDecimal userId, List<BigDecimal> roleId);

    boolean isPwdSame(BigDecimal userId, String pwd);
}
