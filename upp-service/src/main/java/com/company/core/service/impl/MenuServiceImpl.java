package com.company.core.service.impl;

import com.company.core.bo.MenuBO;
import com.company.core.entity.SysFunctionDAO;
import com.company.core.service.IFunctionService;
import com.company.core.service.IRoleService;
import com.company.core.service.MenuService;
import com.company.core.service.UserAuthService;
import com.company.core.util.BeanUtils;
import com.company.core.vo.FunctionTree;
import com.company.core.vo.FunctionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by APPLE on 16/1/12.
 */
@Slf4j
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    IFunctionService functionService;

    @Autowired
    IRoleService service;

    @Override
    public List<FunctionTree> getAllFunctionByRoleId(BigDecimal roleId) {
        List<FunctionVO> result = service.getFunctionByRole(roleId);
        return getMenu(result);
    }

    public List<MenuBO> getAllEnabledMenuByUserId(BigDecimal userid) {
        List<SysFunctionDAO> funcByUsers = userAuthService.getAuthByUserId(userid);
        List<FunctionVO> functionDAOList = BeanUtils.copyList(funcByUsers, FunctionVO.class);
        List<FunctionTree> trees = getMenu(functionDAOList);
        return BeanUtils.copyList(trees, MenuBO.class);
    }

    private List<FunctionTree> getMenu(List<FunctionVO> functionDAOList) {
        List<FunctionTree> menuBOList = new ArrayList<>();
        Optional.ofNullable(functionDAOList).ifPresent(funcs -> {
            funcs.stream().parallel()
                    .filter(fun -> "A".equalsIgnoreCase(fun.getGrade()))
                    .map(fun -> {
                        try {
                            FunctionTree menu = new FunctionTree();
                            BeanUtils.copy(fun, menu);
                            menuBOList.add(menu);
                            return menu;
                        } catch (Exception e) {
                        }
                        return null;
                    })
                    .filter(menu -> menu != null)
                    .forEach(menu -> {
                        if (menu.getSubFunc() == null) menu.setSubFunc(new ArrayList<>());
                        List<FunctionTree> subList = menu.getSubFunc();
                        funcs.stream()
                                .filter(func -> func.getParentId().toString().equals(menu.getId().toString()))
                                .map(subFunc -> {
                                    try {
                                        FunctionTree subMenu = new FunctionTree();
                                        BeanUtils.copy(subFunc, subMenu);
                                        subList.add(subMenu);
                                        return subMenu;
                                    } catch (Exception e) {
                                    }
                                    return null;
                                })
                                .filter(subMenu -> subMenu != null)
                                .forEach(subMenu -> {
                                    if (subMenu.getSubFunc() == null) subMenu.setSubFunc(new ArrayList<>());
                                    List<FunctionTree> subListc = subMenu.getSubFunc();
                                    funcs.stream().parallel()
                                            .filter(func -> func.getParentId().toString().equals(subMenu.getId().toString()))
                                            .forEach(subFunc -> {
                                                try {
                                                    FunctionTree subMenuc = new FunctionTree();
                                                    BeanUtils.copy(subFunc, subMenuc);
                                                    subListc.add(subMenuc);
                                                } catch (Exception e) {
                                                }
                                            });
                                    Collections.sort(subListc);
                                });
                        Collections.sort(subList);
                    });
        });
        Collections.sort(menuBOList);
        return menuBOList;
    }
}
