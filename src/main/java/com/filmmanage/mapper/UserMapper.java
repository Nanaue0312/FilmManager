package com.filmmanage.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.filmmanage.pojo.User;

/**
 * @author Zcy
 * @createTime 2022/12/22 9:42
 * @description UserDAO层
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
