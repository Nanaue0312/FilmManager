package com.filmmanage.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.filmmanage.pojo.News;

/**
 * @author Zcy
 * @description 针对表【news】的数据库操作Mapper
 * @createDate 2023-01-11 19:43:42
 * @Entity com.filmmanage.filmmanagespringboot.pojo.News
 */
@Repository
public interface NewsMapper extends BaseMapper<News> {

}
