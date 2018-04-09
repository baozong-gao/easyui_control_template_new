package com.company.core.mapper;

import com.company.core.entity.SysRoleDAO;
import com.company.core.entity.SysRoleDAOExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleDAOMapper {
    int countByExample(SysRoleDAOExample example);

    int deleteByExample(SysRoleDAOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysRoleDAO record);

    int insertSelective(SysRoleDAO record);

    List<SysRoleDAO> selectByExample(SysRoleDAOExample example);

    SysRoleDAO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") SysRoleDAO record, @Param("example") SysRoleDAOExample example);

    int updateByExample(@Param("record") SysRoleDAO record, @Param("example") SysRoleDAOExample example);

    int updateByPrimaryKeySelective(SysRoleDAO record);

    int updateByPrimaryKey(SysRoleDAO record);
}