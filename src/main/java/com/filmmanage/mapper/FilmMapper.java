package com.filmmanage.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.filmmanage.pojo.Film;

/**
 * @author Zcy
 * @description 针对表【film】的数据库操作Mapper
 * @createDate 2022-12-31 20:34:32
 * @Entity com.filmmanage.filmmanagespringboot.pojo.Film
 */
@Repository
public interface FilmMapper extends BaseMapper<Film> {

}
