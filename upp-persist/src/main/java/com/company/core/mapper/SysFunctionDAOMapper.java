package com.company.core.mapper;

import com.company.core.entity.SysFunctionDAO;
import com.company.core.entity.SysFunctionDAOExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFunctionDAOMapper {
    int countByExample(SysFunctionDAOExample example);

    int deleteByExample(SysFunctionDAOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysFunctionDAO record);

    int insertSelective(SysFunctionDAO record);

    List<SysFunctionDAO> selectByExample(SysFunctionDAOExample example);

    SysFunctionDAO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") SysFunctionDAO record, @Param("example") SysFunctionDAOExample example);

    int updateByExample(@Param("record") SysFunctionDAO record, @Param("example") SysFunctionDAOExample example);

    int updateByPrimaryKeySelective(SysFunctionDAO record);

    int updateByPrimaryKey(SysFunctionDAO record);
}