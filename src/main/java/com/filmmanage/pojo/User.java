package com.filmmanage.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户表
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7527700176947621828L;
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /// **
    // * 用户状态
    // * 0 -> 正常
    // */
    // private Integer userStatus;
    /// **
    // * 用户创建时间
    // */
    // private Date createTime;
    /// **
    // * 用户更新时间
    // */
    // private Date updateTime;
    /// **
    // * 是否删除
    // */
    // private Integer isDelete;
    /// **
    // * 用户角色 0 -> 普通用户，1 -> 管理员
    // */
    // private Integer userRole;
}
