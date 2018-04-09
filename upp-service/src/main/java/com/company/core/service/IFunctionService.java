package com.company.core.service;

import com.company.core.sysexception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: gaobaozong
 * @Description: 权限服务接口
 * @Date: Created in 2017/12/15 - 11:00
 * @Version: V1.0-SNAPSHOT
 */
public interface IFunctionService<D, B> {

    int count(B form);

    List<D> search(B form);

    int delete(BigDecimal id) throws PersistentException;

    int insert(D func) throws PersistentException;

    D searchById(BigDecimal id);

    int update(D func) throws PersistentException;
}
