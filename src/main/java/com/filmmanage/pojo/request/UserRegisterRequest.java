package com.filmmanage.pojo.request;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 625810327375889417L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;

}
