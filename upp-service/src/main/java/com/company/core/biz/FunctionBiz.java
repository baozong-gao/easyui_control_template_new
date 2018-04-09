package com.company.core.biz;

import com.company.core.entity.SysFunctionDAO;
import com.company.core.entity.SysFunctionDAOExample;
import com.company.core.mapper.SysFunctionDAOMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: gaobaozong
 * @Description: 权限 业务操作接口 与service配合使用
 * @Date: Created in 2017/12/28 - 11:19
 * @Version: V1.0-SNAPSHOT
 */
@Service
public class FunctionBiz {

    @Autowired
    SysFunctionDAOMapper sysFunctionDAOMapper;

    /**
     * @Author: gaobaozong
     * @Description: 查询所有父级标签, 默认标识是a b
     * @Date: Created in 2017/12/28 - 11:30
     * @param: grade  标签等级
     * @return:
     */
    public List<SysFunctionDAO> searchParentFunction(String grade) {
        if (StringUtils.isBlank(grade)) {
            grade = "C";
        }

        String ParentFunction = String.valueOf((char) (grade.toCharArray()[0] - 1));

        SysFunctionDAOExample example = new SysFunctionDAOExample();
        example.createCriteria().andGradeEqualTo(ParentFunction);
        List<SysFunctionDAO> funcs = sysFunctionDAOMapper.selectByExample(example);
        return Optional.ofNullable(funcs).filter(_f -> _f.size() > 0).orElseGet(() -> {
            List<SysFunctionDAO> result = new ArrayList<>();
            SysFunctionDAO topFunc = new SysFunctionDAO();
            topFunc.setId(BigDecimal.ZERO);
            topFunc.setName("顶级");
            result.add(topFunc);
            return result;
        });
    }
}
