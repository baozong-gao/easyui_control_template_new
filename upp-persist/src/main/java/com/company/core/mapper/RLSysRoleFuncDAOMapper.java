package com.company.core.mapper;

import com.company.core.entity.RLSysRoleFuncDAO;
import com.company.core.entity.RLSysRoleFuncDAOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RLSysRoleFuncDAOMapper {
    int countByExample(RLSysRoleFuncDAOExample example);

    int deleteByExample(RLSysRoleFuncDAOExample example);

    int insert(RLSysRoleFuncDAO record);

    int insertSelective(RLSysRoleFuncDAO record);

    List<RLSysRoleFuncDAO> selectByExample(RLSysRoleFuncDAOExample example);

    int updateByExampleSelective(@Param("record") RLSysRoleFuncDAO record, @Param("example") RLSysRoleFuncDAOExample example);

    int updateByExample(@Param("record") RLSysRoleFuncDAO record, @Param("example") RLSysRoleFuncDAOExample example);
}