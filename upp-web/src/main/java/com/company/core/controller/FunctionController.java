package com.company.core.controller;


import com.github.pagehelper.PageHelper;
import com.company.core.biz.FunctionBiz;
import com.company.core.bo.FunctionBO;
import com.company.core.constant.ShiroPermissionsConstant;
import com.company.core.dto.FunctionDTO;
import com.company.core.entity.SysFunctionDAO;
import com.company.core.service.IFunctionService;
import com.company.core.springUtil.DateEditor;
import com.company.core.util.BeanUtils;
import com.company.core.util.ReturnUtil;
import com.company.core.vo.FunctionVO;
import com.company.core.vo.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("fun")
@Transactional(rollbackFor = Exception.class)
public class FunctionController {

    @Autowired
    IFunctionService funcService;

    @Autowired
    FunctionBiz functionBiz;

    @Autowired
    private Validator validator;

    @RequestMapping("page")
    @RequiresPermissions(ShiroPermissionsConstant.FUNC_QUERY)
    public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        FunctionBO form = new FunctionBO();

        int pageCurrent = Integer.parseInt(form.getPage());
        int pageSize = Integer.parseInt(form.getRows());
        int size = funcService.count(form);
        Pagination<FunctionVO> page = new Pagination<>(size, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<SysFunctionDAO> userList = funcService.search(form);
        List<FunctionVO> result = BeanUtils.copyList(userList, FunctionVO.class);
        page.addResult(result);
        request.setAttribute("pageUser", page);

        request.setAttribute("rout", form);
        return "fun/listPage";
    }

    @RequestMapping("search")
    @RequiresPermissions(ShiroPermissionsConstant.FUNC_QUERY)
    public String doSelect(@ModelAttribute("rout") FunctionBO form, HttpSession session, HttpServletRequest request,
                           HttpServletResponse response) {

        int pageCurrent = Integer.parseInt(form.getPage());
        int pageSize = Integer.parseInt(form.getRows());
        int size = funcService.count(form);
        Pagination<FunctionVO> page = new Pagination<>(size, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<SysFunctionDAO> userList = funcService.search(form);
        List<FunctionVO> result = BeanUtils.copyList(userList, FunctionVO.class);
        page.addResult(result);

        ReturnUtil.retJson(response, BeanUtils.object2Json(page));
        return null;
    }


    @RequestMapping("addpage")
    @RequiresPermissions(ShiroPermissionsConstant.FUNC_ADD)
    public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        SysFunctionDAO dao = new SysFunctionDAO();
        request.setAttribute("rout", dao);

        return "fun/addPage";
    }


    @RequestMapping("add")
    @RequiresPermissions(ShiroPermissionsConstant.FUNC_ADD)
    public String doAdd(@ModelAttribute("rout") FunctionDTO dto, BindingResult result, HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        BeanUtils.validateJSR303(validator, dto);

        SysFunctionDAO dao = new SysFunctionDAO();
        BeanUtils.copy(dto, dao);
        funcService.insert(dao);
        String succeed = ReturnUtil.succeed();
        ReturnUtil.retJson(response, succeed);
        return null;
    }

    @RequestMapping("delete/{id:.*}")
    @RequiresPermissions(ShiroPermissionsConstant.FUNC_DEL)
    public String deleteUser(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        funcService.delete(new BigDecimal(id));
        String message = ReturnUtil.succeedDel();
        ReturnUtil.retJson(response, message);
        return null;
    }

    @RequestMapping("updatepage/{id:.*}")
    @RequiresPermissions(ShiroPermissionsConstant.FUNC_UP)
    public String doUpdateUserPage(@PathVariable String id, HttpSession session, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        SysFunctionDAO dao = (SysFunctionDAO) funcService.searchById(new BigDecimal(id));
        FunctionVO vo = new FunctionVO();
        BeanUtils.copy(dao, vo);
        request.setAttribute("rout", vo);


        List<SysFunctionDAO> parentFuncs = functionBiz.searchParentFunction(dao.getGrade());
        List<FunctionVO> parentVos = BeanUtils.copyList(parentFuncs, FunctionVO.class);
        parentVos = Optional.ofNullable(parentVos)
                .map(_parent -> _parent.stream().map(_p -> {
                    _p.setSelect(_p.getId().equals(dao.getParentId()));
                    return _p;
                }).collect(Collectors.toList()))
                .orElseGet(null);
        request.setAttribute("selectParents", parentVos);
        return "fun/updatePage";
    }

    @RequestMapping("update")
    @RequiresPermissions(ShiroPermissionsConstant.FUNC_UP)
    public String doUpdate(@ModelAttribute("rout") FunctionDTO dto, HttpSession session, HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        BeanUtils.validateJSR303(validator, dto);
        SysFunctionDAO dao = new SysFunctionDAO();
        BeanUtils.copy(dto, dao);
        funcService.update(dao);
        String succeed = ReturnUtil.succeed();
        ReturnUtil.retJson(response, succeed);
        return null;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        String dateFormat = "yyyy-MM-dd";
        binder.registerCustomEditor(Date.class, new DateEditor(dateFormat));
    }

    @RequestMapping("searchparent/{grade:.*}")
    public String getParent(@PathVariable("grade") String grade,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        List<SysFunctionDAO> parentFuncs = functionBiz.searchParentFunction(grade);
        List<FunctionVO> parentVos = BeanUtils.copyList(parentFuncs, FunctionVO.class);
        ReturnUtil.retJson(response, BeanUtils.object2Json(parentVos));
        return null;
    }
}
