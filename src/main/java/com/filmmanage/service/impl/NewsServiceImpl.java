package com.filmmanage.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.filmmanage.mapper.NewsMapper;
import com.filmmanage.pojo.News;
import com.filmmanage.service.NewsService;

/**
 * @author Zcy
 * @createTime 2023/1/11 19:50
 * @description
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public Boolean checkNews(News news) {
        return (StringUtils.hasLength(news.getContent()) || StringUtils.hasLength(news.getAuthor())
            || StringUtils.hasLength(news.getReleaseDate()) || StringUtils.hasLength(news.getTitle()));
    }
}
