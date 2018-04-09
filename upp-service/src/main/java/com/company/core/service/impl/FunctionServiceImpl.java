package com.company.core.service.impl;

import com.company.core.bo.FunctionBO;
import com.company.core.entity.SysFunctionDAO;
import com.company.core.entity.SysFunctionDAOExample;
import com.company.core.mapper.SeqMapper;
import com.company.core.mapper.SysFunctionDAOMapper;
import com.company.core.service.IFunctionService;
import com.company.core.sysexception.PersistentException;
import com.company.core.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: gaobaozong
 * @Description: 权限查询服务
 * @Date: Created in 2017/12/15 - 11:05
 * @Version: V1.0-SNAPSHOT
 */
@Slf4j
@Service("functionService")
public class FunctionServiceImpl implements IFunctionService<SysFunctionDAO, FunctionBO> {

    @Autowired
    SysFunctionDAOMapper mapper;

    @Autowired
    SeqMapper seqMapper;


    private SysFunctionDAOExample getSearchFiled(FunctionBO bo) {
        SysFunctionDAOExample example = new SysFunctionDAOExample();
        SysFunctionDAOExample.Criteria criteria = example.createCriteria();
        if (bo == null) {
            return example;
        }
        if (BeanUtils.filedNotNull(bo.getId())) {
            criteria.andIdEqualTo(new BigDecimal(bo.getId()));
        }
        if (BeanUtils.filedNotNull(bo.getName())) {
            criteria.andNameEqualTo(bo.getName());
        }
        if (BeanUtils.filedNotNull(bo.getCode())) {
            criteria.andCodeEqualTo(bo.getCode());
        }
        if (BeanUtils.filedNotNull(bo.getGrade())) {
            criteria.andGradeEqualTo(bo.getGrade());
        }
        if (BeanUtils.filedNotNull(bo.getParentId())) {
            criteria.andParentIdEqualTo(new BigDecimal(bo.getParentId()));
        }
        if (BeanUtils.filedNotNull(bo.getStatus())) {
            criteria.andStatusEqualTo(bo.getStatus());
        }
        if (BeanUtils.filedNotNull(bo.getType())) {
            criteria.andTypeEqualTo(bo.getType());
        }
        return example;
    }

    @Override
    public int count(FunctionBO form) {
        SysFunctionDAOExample ex = getSearchFiled(form);
        return mapper.countByExample(ex);
    }

    @Override
    public List<SysFunctionDAO> search(FunctionBO form) {
        SysFunctionDAOExample ex = getSearchFiled(form);
        List<SysFunctionDAO> result = mapper.selectByExample(ex);
        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public int delete(BigDecimal id) throws PersistentException {
        if (id == null) {
            throw new PersistentException("删除权限失败：参数为空");
        }
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysFunctionDAO function) throws PersistentException {
        if (function == null) {
            throw new PersistentException("增加权限失败：参数为空");
        }
        Date current = new Date();
        int id = seqMapper.getFunctionSeq();
        function.setId(new BigDecimal(id));
        function.setCreateTime(current);
        function.setCreateUser("1");
        function.setUpdateTime(current);
        function.setUpdateUser("1");
        function.setVersion(BigDecimal.ONE);
        return mapper.insertSelective(function);
    }

    @Override
    public SysFunctionDAO searchById(BigDecimal id) {
        if (id == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysFunctionDAO function) throws PersistentException {
        if (function == null || function.getId() == null) {
            throw new PersistentException("更新权限失败：id为空");
        }
        return mapper.updateByPrimaryKeySelective(function);
    }
}
