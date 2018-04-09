package com.company.core.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: gaobaozong
 * @Description: 权限树
 * @Date: Created in 2017/12/25 - 20:08
 * @Version: V1.0-SNAPSHOT
 */
@Data
public class FunctionTree extends FunctionVO implements Comparable<FunctionTree>{
    List<FunctionTree> subFunc;

    @Override
    public int compareTo(FunctionTree o) {
        if(o == null){
            return 0;
        }
        if (getOrderBy().compareTo(o.getOrderBy()) != 0) {
            return getOrderBy().compareTo(o.getOrderBy());
        } else {
            return getId().compareTo(o.getId());
        }
    }
}
