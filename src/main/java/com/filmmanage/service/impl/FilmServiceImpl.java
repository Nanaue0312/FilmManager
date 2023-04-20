package com.filmmanage.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.filmmanage.mapper.FilmMapper;
import com.filmmanage.pojo.Film;
import com.filmmanage.service.FilmService;

/**
 * @author Zcy
 * @createTime 2022/12/31 20:36
 * @description FilmService类
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {

}
