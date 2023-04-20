package com.filmmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.filmmanage.pojo.News;

/**
 * @author Zcy
 * @createTime 2023/1/11 19:50
 * @description
 */
public interface NewsService extends IService<News> {
    /**
     * 检验news对象的值是否为空
     *
     * @param news
     * @return
     */
    Boolean checkNews(News news);
}
