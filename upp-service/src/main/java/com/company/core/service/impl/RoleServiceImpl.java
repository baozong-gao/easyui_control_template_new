package com.company.core.service.impl;

import com.company.core.bo.SysRoleBO;
import com.company.core.entity.*;
import com.company.core.mapper.*;
import com.company.core.service.IRoleService;
import com.company.core.sysexception.PersistentException;
import com.company.core.util.BeanUtils;
import com.company.core.vo.FunctionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: gaobaozong
 * @Description: 角色服务
 * @Date: Created in 2017/12/19 - 10:50
 * @Version: V1.0-SNAPSHOT
 */
@Slf4j
@Service("roleService")
public class RoleServiceImpl implements IRoleService<SysRoleDAO, SysRoleBO> {

    @Autowired
    SysRoleDAOMapper sysRoleDAOMapper;

    @Autowired
    RLSysRoleFuncDAOMapper rlSysRoleFuncDAOMapper;

    @Autowired
    SysFunctionDAOMapper sysFunctionDAOMapper;

    @Autowired
    SeqMapper seqMapper;

    @Autowired
    RLSysUsrRoleDAOMapper rlSysUsrRoleDAOMapper;

    private SysRoleDAOExample getSearchFiled(SysRoleBO bo) {
        SysRoleDAOExample example = new SysRoleDAOExample();
        SysRoleDAOExample.Criteria criteria = example.createCriteria();
        if (bo == null) {
            return example;
        }
        if (BeanUtils.filedNotNull(bo.getId())) {
            criteria.andIdEqualTo(new BigDecimal(bo.getId()));
        }
        if (BeanUtils.filedNotNull(bo.getRemark())) {
            criteria.andRemarkEqualTo(bo.getRemark());
        }
        if (BeanUtils.filedNotNull(bo.getName())) {
            criteria.andNameEqualTo(bo.getName());
        }
        if (BeanUtils.filedNotNull(bo.getStatus())) {
            criteria.andStatusEqualTo(bo.getStatus());
        }
        return example;

    }

    @Override
    public int count(SysRoleBO form) {
        SysRoleDAOExample example = getSearchFiled(form);
        return sysRoleDAOMapper.countByExample(example);
    }

    @Override
    public List<SysRoleDAO> search(SysRoleBO form) {
        SysRoleDAOExample example = getSearchFiled(form);
        List<SysRoleDAO> result = sysRoleDAOMapper.selectByExample(example);
        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public int delete(BigDecimal id) throws PersistentException {
        if (id == null) {
            throw new PersistentException("删除角色失败：参数为空");
        }
        return sysRoleDAOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysRoleDAO sysRoleDAO) throws PersistentException {
        if (sysRoleDAO == null) {
            throw new PersistentException("增加角色失败：参数为空");
        }
        Date currentTime = new Date();
        int id = seqMapper.getRoleSeq();
        sysRoleDAO.setId(new BigDecimal(id));
        sysRoleDAO.setCreateTime(currentTime);
        sysRoleDAO.setUpdateTime(currentTime);
        sysRoleDAO.setCreateUser("1");
        sysRoleDAO.setUpdateUser("1");
        sysRoleDAO.setVersion(BigDecimal.ONE);
        int size = sysRoleDAOMapper.insertSelective(sysRoleDAO);
        if (size == 1) {
            return id;
        }
        throw new PersistentException("增加角色失败：数据插入失败");
    }

    @Override
    public SysRoleDAO searchById(BigDecimal id) {
        if (id == null) {
            return null;
        }
        return sysRoleDAOMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysRoleDAO sysRoleDAO) throws PersistentException {
        if (sysRoleDAO == null || sysRoleDAO.getId() == null) {
            throw new PersistentException("更新角色失败：id为空");
        }
        sysRoleDAO.setUpdateTime(new Date());
        return sysRoleDAOMapper.updateByPrimaryKeySelective(sysRoleDAO);
    }

    @Override
    public List getFunctionByRole(BigDecimal id) {
        Set<String> roleFuncIDs = null;
        if (id != null) {
            RLSysRoleFuncDAOExample example = new RLSysRoleFuncDAOExample();
            example.createCriteria().andSysRoleIdEqualTo(id);
            List<RLSysRoleFuncDAO> roleFunc = rlSysRoleFuncDAOMapper.selectByExample(example);
            roleFuncIDs = roleFunc.stream().map(rf -> rf.getSysFuncId().toString()).collect(Collectors.toSet());
        }
        Set<String> _roleFuncIDs = Optional.ofNullable(roleFuncIDs).orElse(new HashSet<>());

        List<SysFunctionDAO> allFunction = sysFunctionDAOMapper.selectByExample(null);
        List<FunctionVO> result = BeanUtils.copyList(allFunction, FunctionVO.class);
        result.stream().forEach(func -> func.setAuth(_roleFuncIDs.contains(func.getId().toString())));

        return result;
    }

    @Override
    @Transactional
    public int upFunctionOnRole(BigDecimal id, List<BigDecimal> func) {
        RLSysRoleFuncDAOExample example = new RLSysRoleFuncDAOExample();
        example.createCriteria().andSysRoleIdEqualTo(id);
        rlSysRoleFuncDAOMapper.deleteByExample(example);

        Optional.ofNullable(func).ifPresent(funcs -> {
            funcs.stream().forEach(fun -> {
                RLSysRoleFuncDAO dao = new RLSysRoleFuncDAO();
                dao.setSysFuncId(fun);
                dao.setSysRoleId(id);
                rlSysRoleFuncDAOMapper.insert(dao);
            });
        });
        return 0;
    }

    @Override
    public int insertRLUserRole(RLSysUsrRoleDAO rlSysUsrRoleDAO){
        return rlSysUsrRoleDAOMapper.insert(rlSysUsrRoleDAO);
    }
}
