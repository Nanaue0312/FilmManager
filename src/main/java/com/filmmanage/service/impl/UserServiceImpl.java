package com.filmmanage.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.filmmanage.constant.UserConstant;
import com.filmmanage.mapper.UserMapper;
import com.filmmanage.pojo.User;
import com.filmmanage.service.UserService;

/**
 * 用户服务实现类
 *
 * @author Zcy
 * @createTime 2022/12/22 9:43
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // 盐值：混淆密码
    private static final String SALT = "zcy";
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param userAccount 用户账户 4-12
     * @param userPassword 用户密码 8-12
     * @param checkPassword 校验密码
     * @return 用户id
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        if (!(StringUtils.hasLength(userAccount) || StringUtils.hasLength(userPassword)
            || StringUtils.hasLength(checkPassword))) {
            return -1;
        }
        if (userAccount.length() < 4 || userAccount.length() > 12) {
            return -1;
        }
        if (userPassword.length() < 8 || userPassword.length() > 12 || checkPassword.length() < 8
            || checkPassword.length() > 12) {
            return -1;
        }

        // 校验账户包含特殊字符
        String validPatrn = "[` ~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPatrn).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 账户不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account", userAccount);
        if (userMapper.selectCount(userQueryWrapper) > 0) {
            return -1;
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        // 3. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        int saveResult = userMapper.insert(user);
        System.out.println("------------------------" + saveResult + "-----------------------");
        if (saveResult < 1) {
            return -1;
        }
        return user.getId();
    }

    /**
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request 请求
     * @return 脱敏后的用户信息
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (!(StringUtils.hasLength(userAccount) || StringUtils.hasLength(userPassword))) {
            return null;
        }
        if (userAccount.length() < 4 || userAccount.length() > 12) {
            return null;
        }
        if (userPassword.length() < 8 || userPassword.length() > 12) {
            return null;
        }

        // 校验账户包含特殊字符
        String validPatrn = "[` ~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPatrn).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        // 3. 查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account", userAccount).eq("user_password", encryptPassword);
        User user = userMapper.selectOne(userQueryWrapper);
        // 用户不存在
        if (user == null) {
            request.getSession().setAttribute("msg", "用户名或密码错误");
            System.out.println(request.getSession().getAttribute("msg"));
            System.out
                .println("-----------------user login failed,userAccount cannot match userPassword-----------------");
            return null;
        }
        // 3. 用户脱敏
        User safetyUser = getSafetyUser(user);
        // 4. 记录用户登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return safetyUser;
    }

    /**
     * 用户脱敏
     *
     * @param originUser 原始用户
     * @return 脱敏后的用户
     */
    @Override
    public User getSafetyUser(User originUser) {
        if (originUser == null) {
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        // safetyUser.setUserStatus(originUser.getUserStatus());
        // safetyUser.setCreateTime(originUser.getCreateTime());
        // safetyUser.setUserRole(originUser.getUserRole());
        return safetyUser;
    }
}
