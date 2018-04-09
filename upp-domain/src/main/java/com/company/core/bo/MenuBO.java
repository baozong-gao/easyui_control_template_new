package com.company.core.bo;

import com.company.core.Enum.FiledAlias;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
  *  @Author: gaobaozong
  *  @Description:  菜单
  *  @Date: Created in 2017/12/14 - 11:37
  */
@Data
public class MenuBO implements Comparable<MenuBO> {
    @FiledAlias("id")
    private String       funcId;
    @FiledAlias("name")
    private String       funcName;
    @FiledAlias("uri")
    private String       funcUrl;
    @FiledAlias("logo")
    private String       funcIcon;
    @FiledAlias("orderBy")
    private BigDecimal   funcPriority;

    private String logo;

    @FiledAlias("subFunc")
    private List<MenuBO> childMenuBOList;


    public int compareTo(MenuBO o) {
        if(o == null){
            return 0;
        }
        if (this.funcPriority.compareTo(o.funcPriority) != 0) {
            return this.funcPriority.compareTo(o.funcPriority);
        } else {
            return this.funcId.compareTo(o.funcId);
        }
    }
}
