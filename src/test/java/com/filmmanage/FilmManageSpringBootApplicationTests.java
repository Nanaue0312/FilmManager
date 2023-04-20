package com.filmmanage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.filmmanage.pojo.News;
import com.filmmanage.service.NewsService;

@SpringBootTest
class FilmManageSpringBootApplicationTests {
    @Autowired
    NewsService newsService;

    @Test
    void testPage() {
        Page<News> newsPage = new Page<>();
        newsPage.setCurrent(1);
        newsPage.setSize(3);
        Page<News> page = newsService.page(newsPage, null);
        page.getRecords().forEach(System.out::println);
    }

}
