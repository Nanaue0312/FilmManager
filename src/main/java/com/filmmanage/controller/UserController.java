package com.filmmanage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.filmmanage.constant.UserConstant;
import com.filmmanage.pojo.User;
import com.filmmanage.pojo.request.UserLoginRequest;
import com.filmmanage.pojo.request.UserRegisterRequest;
import com.filmmanage.service.UserService;

/**
 * @author Zcy
 * @createTime 2022/12/22 9:38
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 处理用户登录
     *
     * @param userLoginRequest 用户登录请求体
     * @param request 请求
     * @return 主页
     */
    @PostMapping("/login")
    public String login(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (!(StringUtils.hasLength(userAccount) || StringUtils.hasLength(userPassword))) {
            return "redirect:/user/toLogin";
        }
        if (userAccount.length() < 4 || userAccount.length() > 12) {
            return "redirect:/user/toLogin";
        }
        if (userPassword.length() < 8 || userPassword.length() > 12) {
            return "redirect:/user/toLogin";
        }
        User user =
            userService.userLogin(userLoginRequest.getUserAccount(), userLoginRequest.getUserPassword(), request);
        if (user == null) {
            request.getSession().setAttribute("msg", "账户名或密码错误！");
            return "redirect:/user/toLogin";
        }
        return "redirect:/";
    }

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求体
     * @return userid
     */
    @PostMapping("/register")
    public String userRegister(UserRegisterRequest userRegisterRequest, Model model) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (userAccount.length() < 4 || userAccount.length() > 12) {
            return "redirect:/user/register.html";
        }
        if (userPassword.length() < 8 || userPassword.length() > 12) {
            return "redirect:/user/login.html";
        }
        if (checkPassword.length() < 8 || checkPassword.length() > 12) {
            return "redirect:/user/login.html";
        }
        model.addAttribute("userId", userService.userRegister(userAccount, userPassword, checkPassword));
        return "redirect:/user/toLogin";
    }

    /**
     * 验证用户是否存在
     *
     * @param userAccount 用户名
     * @return msg信息
     */
    @RequestMapping("/checkUserAccount/{userAccount}")
    @ResponseBody
    public String checkUsername(@PathVariable("userAccount") String userAccount) {
        if (userAccount.length() < 4 || userAccount.length() > 12) {
            return "用户账户长度应在4-12";
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account", userAccount);
        long count = userService.count(userQueryWrapper);
        if (count < 1) {
            return "用户账户可以使用";
        }
        return "用户账户已存在";
    }

    @GetMapping("/login-out")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return "redirect:/";
    }

    @PostMapping("/update")
    @Transactional
    public String saveUser(User user, HttpServletRequest request) {
        if (user == null) {
            return "redirect:/";
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", user.getId());
        userService.update(user, userQueryWrapper);
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return "redirect:/";
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    // public boolean isAdmin(HttpServletRequest request) {
    // User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
    // if (user == null || user.getUserRole() != UserConstant.ADMIN_ROLE) {
    // return false;
    // }
    // return true;
    // }
}
