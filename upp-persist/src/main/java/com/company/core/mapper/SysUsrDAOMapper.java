package com.company.core.mapper;

import com.company.core.entity.SysUsrDAO;
import com.company.core.entity.SysUsrDAOExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUsrDAOMapper {
    int countByExample(SysUsrDAOExample example);

    int deleteByExample(SysUsrDAOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysUsrDAO record);

    int insertSelective(SysUsrDAO record);

    List<SysUsrDAO> selectByExample(SysUsrDAOExample example);

    SysUsrDAO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") SysUsrDAO record, @Param("example") SysUsrDAOExample example);

    int updateByExample(@Param("record") SysUsrDAO record, @Param("example") SysUsrDAOExample example);

    int updateByPrimaryKeySelective(SysUsrDAO record);

    int updateByPrimaryKey(SysUsrDAO record);
}