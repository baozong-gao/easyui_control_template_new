package com.company.core.mapper;

import com.company.core.entity.RLSysUsrRoleDAO;
import com.company.core.entity.RLSysUsrRoleDAOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RLSysUsrRoleDAOMapper {
    int countByExample(RLSysUsrRoleDAOExample example);

    int deleteByExample(RLSysUsrRoleDAOExample example);

    int insert(RLSysUsrRoleDAO record);

    int insertSelective(RLSysUsrRoleDAO record);

    List<RLSysUsrRoleDAO> selectByExample(RLSysUsrRoleDAOExample example);

    int updateByExampleSelective(@Param("record") RLSysUsrRoleDAO record, @Param("example") RLSysUsrRoleDAOExample example);

    int updateByExample(@Param("record") RLSysUsrRoleDAO record, @Param("example") RLSysUsrRoleDAOExample example);
}