package com.company.core.service.impl;

import com.company.core.bo.UserBO;
import com.company.core.entity.*;
import com.company.core.mapper.RLSysUsrRoleDAOMapper;
import com.company.core.mapper.SeqMapper;
import com.company.core.mapper.SysRoleDAOMapper;
import com.company.core.mapper.SysUsrDAOMapper;
import com.company.core.service.IUserService;
import com.company.core.sysexception.PersistentException;
import com.company.core.util.BeanUtils;
import com.company.core.util.EncryptUtils;
import com.company.core.vo.SysRoleVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: gaobaozong
 * @Description: 用户信息服务
 * @Date: Created in 2017/12/21 - 14:07
 * @Version: V1.0-SNAPSHOT
 */
@Service("userService")
public class UserServiceImpl implements IUserService<SysUsrDAO, UserBO> {

    @Autowired
    SeqMapper seqMapper;

    @Autowired
    SysUsrDAOMapper sysUsrDAOMapper;

    @Autowired
    SysRoleDAOMapper roleDAOMapper;

    @Autowired
    RLSysUsrRoleDAOMapper rlSysUsrRoleDAOMapper;

    private SysUsrDAOExample getSearchFiled(UserBO bo) {
        SysUsrDAOExample example = new SysUsrDAOExample();
        SysUsrDAOExample.Criteria criteria = example.createCriteria();
        if (bo == null) {
            return example;
        }
        if (BeanUtils.filedNotNull(bo.getId())) {
            criteria.andIdEqualTo(new BigDecimal(bo.getId()));
        }
        if (BeanUtils.filedNotNull(bo.getName())) {
            criteria.andNameEqualTo(bo.getName());
        }
        if (BeanUtils.filedNotNull(bo.getEmail())) {
            criteria.andEmailEqualTo(bo.getEmail());
        }
        if (BeanUtils.filedNotNull(bo.getPwd())) {
            criteria.andPwdEqualTo(bo.getPwd());
        }
        if (BeanUtils.filedNotNull(bo.getRemark())) {
            criteria.andRemarkEqualTo(bo.getRemark());
        }
        if (BeanUtils.filedNotNull(bo.getUserStatus())) {
            criteria.andUserStatusEqualTo(bo.getUserStatus());
        }
        if (BeanUtils.filedNotNull(bo.getUserType())) {
            criteria.andUserTypeEqualTo(bo.getUserType());
        }
        if (BeanUtils.filedNotNull(bo.getUserTypeId())) {
            criteria.andUserTypeIdEqualTo(new BigDecimal(bo.getUserTypeId()));
        }
        return example;
    }

    @Override
    public int count(UserBO form) {
        SysUsrDAOExample example = getSearchFiled(form);
        return sysUsrDAOMapper.countByExample(example);
    }

    @Override
    public List<SysUsrDAO> search(UserBO form) {
        SysUsrDAOExample example = getSearchFiled(form);
        return sysUsrDAOMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(BigDecimal id) throws PersistentException {
        if (id == null) {
            throw new PersistentException("删除失败：参数为空");
        }
        return sysUsrDAOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUsrDAO sysUsrDAO) throws PersistentException {
        if (sysUsrDAO == null) {
            throw new PersistentException("增加失败：参数为空");
        }
        Date currentTime = new Date();
        int id = seqMapper.getSysUsrSeq();
        int typeId = seqMapper.getSysUsrTypeSeq();
        sysUsrDAO.setId(new BigDecimal(id));
        sysUsrDAO.setUserTypeId(new BigDecimal(typeId));
        sysUsrDAO.setPwd(EncryptUtils.encryptPwd(sysUsrDAO.getPwd()));
        sysUsrDAO.setCreateUser("1");
        sysUsrDAO.setUpdateUser("1");
        sysUsrDAO.setVersion(BigDecimal.ONE);
        sysUsrDAO.setCreateTime(currentTime);
        sysUsrDAO.setUpdateTime(currentTime);
        int size = sysUsrDAOMapper.insertSelective(sysUsrDAO);
        if (size == 1) {
            return id;
        }
        throw new PersistentException("增加用户失败：数据插入失败");
    }

    @Override
    public int insertOperator(SysUsrDAO sysUsrDAO) throws PersistentException {
        if (sysUsrDAO == null) {
            throw new PersistentException("增加失败：参数为空");
        }
        Date currentTime = new Date();
        int id = seqMapper.getSysUsrSeq();
        sysUsrDAO.setId(new BigDecimal(id));
        sysUsrDAO.setPwd(EncryptUtils.encryptPwd(sysUsrDAO.getPwd()));
        sysUsrDAO.setCreateUser("1");
        sysUsrDAO.setUpdateUser("1");
        sysUsrDAO.setVersion(BigDecimal.ONE);
        sysUsrDAO.setCreateTime(currentTime);
        sysUsrDAO.setUpdateTime(currentTime);
        int size = sysUsrDAOMapper.insertSelective(sysUsrDAO);
        if (size == 1) {
            return id;
        }
        throw new PersistentException("增加用户失败：数据插入失败");
    }
    @Override
    public int insertUser(SysUsrDAO sysUsrDAO) throws PersistentException {
        if (sysUsrDAO == null) {
            throw new PersistentException("增加失败：参数为空");
        }
        int size = sysUsrDAOMapper.insertSelective(sysUsrDAO);
        if (size == 1) {
            return 1;
        }
        throw new PersistentException("增加用户失败：数据插入失败");
    }
    @Override
    public SysUsrDAO searchById(BigDecimal id) {
        if (id == null) {
            return null;
        }
        return sysUsrDAOMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SysUsrDAO sysUsrDAO) throws PersistentException {
        if (sysUsrDAO == null || sysUsrDAO.getId() == null) {
            throw new PersistentException("更新失败：id为空");
        }
        if (StringUtils.isNotBlank(sysUsrDAO.getPwd())) {
            sysUsrDAO.setPwd(EncryptUtils.encryptPwd(sysUsrDAO.getPwd()));
        }
        sysUsrDAO.setUpdateTime(new Date());
        return sysUsrDAOMapper.updateByPrimaryKeySelective(sysUsrDAO);
    }

    @Override
    public List getRoleByUserId(BigDecimal id) {

        RLSysUsrRoleDAOExample example = new RLSysUsrRoleDAOExample();
        RLSysUsrRoleDAOExample.Criteria criteria = example.createCriteria();
        if (id != null) {
            criteria.andSysUsrIdEqualTo(id);
        }
        List<RLSysUsrRoleDAO> ur = rlSysUsrRoleDAOMapper.selectByExample(example);
        Set<BigDecimal> roleIDs = ur.stream().map(rf -> rf.getSysRoleId()).collect(Collectors.toSet());

        List<SysRoleDAO> allRoles = roleDAOMapper.selectByExample(null);
        List<SysRoleVO> result = BeanUtils.copyList(allRoles, SysRoleVO.class);
        result.stream().forEach(role -> role.setAuth(roleIDs.contains(role.getId())));

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upRoleOnUser(BigDecimal uid, List<BigDecimal> rids) {
        RLSysUsrRoleDAOExample example = new RLSysUsrRoleDAOExample();
        example.createCriteria().andSysUsrIdEqualTo(uid);
        rlSysUsrRoleDAOMapper.deleteByExample(example);

        Optional.ofNullable(rids).ifPresent((rid) -> {
            rid.stream().forEach(_rid -> {
                RLSysUsrRoleDAO record = new RLSysUsrRoleDAO();
                record.setSysRoleId(_rid);
                record.setSysUsrId(uid);
                rlSysUsrRoleDAOMapper.insert(record);
            });
        });
    }

    @Override
    public boolean isPwdSame(BigDecimal userId, String pwd) {
        return Optional.ofNullable(userId)
                .map(_id -> searchById(_id))
                .map(user -> user.getPwd())
                .filter(_pwd -> _pwd.equals(EncryptUtils.encryptPwd(pwd)))
                .map(tmp -> true)
                .orElse(false);
    }
}
