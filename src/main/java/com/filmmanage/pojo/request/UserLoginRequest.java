package com.filmmanage.pojo.request;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录请求体
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 8760958017089236675L;
    String userAccount;
    String userPassword;

}
