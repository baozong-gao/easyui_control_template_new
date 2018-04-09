package com.company.core.controller;


import com.company.core.bo.UserBO;
import com.company.core.constant.ShiroPermissionsConstant;
import com.company.core.constant.SystemConstant;
import com.company.core.dto.UserDTO;
import com.company.core.entity.SysUsrDAO;
import com.company.core.service.IUserService;
import com.company.core.shiro.MonitorRealm;
import com.company.core.springUtil.DateEditor;
import com.company.core.util.BeanUtils;
import com.company.core.util.ReturnUtil;
import com.company.core.vo.Pagination;
import com.company.core.vo.SysRoleVO;
import com.company.core.vo.UserVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("user")
@Transactional(rollbackFor = Exception.class)
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    private Validator validator;

    /**
     * 展示系统用户
     *
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("page")
    @RequiresPermissions(ShiroPermissionsConstant.USER_QUERY)
    public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        UserBO form = new UserBO();

        int pageCurrent = Integer.parseInt(form.getPage());
        int pageSize = Integer.parseInt(form.getRows());
        int size = userService.count(form);
        Pagination<UserVO> page = new Pagination<>(size, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<SysUsrDAO> userList = userService.search(form);
        List<UserVO> result = BeanUtils.copyList(userList, UserVO.class);
        page.addResult(result);
        request.setAttribute("pageUser", page);

        request.setAttribute("rout", form);
        return "user/listPage";
    }

    /**
     * 查询系统用户
     *
     * @param form
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("search")
    @RequiresPermissions(ShiroPermissionsConstant.USER_QUERY)
    public String doSelect(@ModelAttribute("rout") UserBO form, HttpSession session, HttpServletRequest request,
                           HttpServletResponse response) {

        int pageCurrent = Integer.parseInt(form.getPage());
        int pageSize = Integer.parseInt(form.getRows());
        int size = userService.count(form);
        Pagination<UserVO> page = new Pagination<>(size, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<SysUsrDAO> userList = userService.search(form);
        List<UserVO> result = BeanUtils.copyList(userList, UserVO.class);
        page.addResult(result);

        ReturnUtil.retJson(response, BeanUtils.object2Json(page));
        return null;
    }


    /**
     * 新增系统用户-跳转链接
     *
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("addpage")
    @RequiresPermissions(ShiroPermissionsConstant.USER_ADD)
    public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        List<SysRoleVO> roles = userService.getRoleByUserId(null);
        roles.stream().forEach(role -> role.setAuth(false));
        request.setAttribute("rout", new UserDTO());
        request.setAttribute("roles", roles);
        return "user/addPage";
    }

    /**
     * 新增系统用户
     *
     * @param userDTO
     * @param result
     * @param session
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("add")
    @RequiresPermissions(ShiroPermissionsConstant.USER_ADD)
    public String doAdd(@ModelAttribute("rout") UserDTO userDTO, BindingResult result, HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        BeanUtils.validateJSR303(validator, userDTO);

        SysUsrDAO dao = new SysUsrDAO();
        BeanUtils.copy(userDTO, dao);
        int id = userService.insert(dao);
        userService.upRoleOnUser(new BigDecimal(id), userDTO.getRoles());
        String succeed = ReturnUtil.succeed();
        ReturnUtil.retJson(response, succeed);
        return null;
    }

    /**
     * 删除系统用户
     *
     * @param id
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("delete/{id:.*}")
    @RequiresPermissions(ShiroPermissionsConstant.USER_DEL)
    public String deleteUser(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userService.delete(new BigDecimal(id));
        String message = ReturnUtil.succeedDel();
        ReturnUtil.retJson(response, message);
        return null;
    }

    /**
     * 更新系统用户
     *
     * @param id
     * @param session
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("updatepage/{id:.*}")
    @RequiresPermissions(ShiroPermissionsConstant.USER_UP)
    public String doUpdateUserPage(@PathVariable String id, HttpSession session, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        SysUsrDAO dao = (SysUsrDAO) userService.searchById(new BigDecimal(id));
        UserVO vo = new UserVO();
        BeanUtils.copy(dao, vo);
        request.setAttribute("rout", vo);

        List<SysRoleVO> roles = userService.getRoleByUserId(new BigDecimal(id));
        request.setAttribute("roles", roles);
        return "user/updatePage";
    }

    /**
     * 更新系统用户
     *
     * @param dto
     * @param session
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("update")
    @RequiresPermissions(ShiroPermissionsConstant.USER_UP)
    public String doUpdate(@ModelAttribute("rout") UserDTO dto, HttpSession session, HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        BeanUtils.validateJSR303(validator, dto);
        SysUsrDAO dao = new SysUsrDAO();
        BeanUtils.copy(dto, dao);
        userService.update(dao);
        userService.upRoleOnUser(dto.getId(), dto.getRoles());
        String succeed = ReturnUtil.succeed();
        ReturnUtil.retJson(response, succeed);
        return null;
    }

    /**
     * @param request
     * @return
     */
    @RequiresPermissions(ShiroPermissionsConstant.USER_UP)
    @RequestMapping(value = "goToChangepwdPage", method = RequestMethod.GET)
    public String goToChangepwdPage(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
        request.setAttribute("user", shiroUser.getUser());
        return "user/changepwd";
    }

//    @RequiresPermissions(ShiroPermissionsConstant.USER_UP)
    @RequestMapping(value = "changepwd", method = RequestMethod.POST)
    public Map changepwd(HttpServletRequest request, HttpServletResponse response) {
        String succeed = ReturnUtil.succeed();
        try {
            String userId = request.getParameter("userid");
            String oldpassword = request.getParameter("oldpassword");
            String pass = request.getParameter("password");
            SysUsrDAO userDAO = (SysUsrDAO) userService.searchById(new BigDecimal(userId));
            if (SystemConstant.ROOT.equals(userDAO.getName()) || userService.isPwdSame(new BigDecimal(userId), oldpassword)) {
                userDAO.setPwd(pass);
                userDAO.setUserStatus("NORMAL");
                userService.update(userDAO);
            } else {
                succeed = ReturnUtil.error("旧密码错误");
            }
        } catch (Exception e) {
            succeed = ReturnUtil.error("重置密码错误");
            log.error("重置密码错误", e);
        }
        ReturnUtil.retJson(response, succeed);
        return null;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        String dateFormat = "yyyy-MM-dd";
        binder.registerCustomEditor(Date.class, new DateEditor(dateFormat));
    }
}
