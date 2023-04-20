package com.filmmanage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.filmmanage.pojo.News;
import com.filmmanage.service.NewsService;
import com.filmmanage.service.impl.PageServiceImpl;

/**
 * @author Zcy
 * @createTime 2023/1/11 19:49
 * @description
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @PostMapping("/saveNews")
    @ResponseBody
    @Transactional
    public String saveNews(@RequestBody News news) {
        if (!newsService.checkNews(news)) {
            return "请输入新闻信息";
        }
        try {
            boolean res = newsService.save(news);
            if (!res) {
                return "新闻保存失败，请重新尝试";
            }
        } catch (Exception e) {
            return "新闻已存在，请不要重复保存";
        }
        return "新闻保存成功";
    }

    /**
     * 新闻列表
     *
     * @param current 当前页码
     * @param request
     * @return 跳转的目标页面
     */
    @GetMapping("/news_info/{current}")
    public String selectNews(@PathVariable("current") Integer current, HttpServletRequest request) {
        if (current == null) {
            current = 1;
        }
        Page<News> newsPage = new Page<>();
        newsPage.setCurrent(current);
        Page<News> page = newsService.page(newsPage, null);
        // 自定义的导航栏
        int[] pageNav = PageServiceImpl.pageNav(current, (int)page.getPages(), 3);
        request.setAttribute("records", page.getRecords());
        request.setAttribute("hasPrevious", page.hasPrevious());
        request.setAttribute("hasNext", page.hasNext());
        request.setAttribute("current", page.getCurrent());
        request.setAttribute("pages", page.getPages());
        request.setAttribute("navigation", pageNav);
        // return page;
        return "news/news_list";
    }

    @GetMapping("toUpdate/{newsId}")
    public String updateNews(@PathVariable("newsId") Integer newsId, Model model) {
        News news = newsService.getById(newsId);
        System.out.println(newsId);
        model.addAttribute("news", news);
        return "news/update_news";
    }

    @PostMapping("/updateNews")
    @ResponseBody
    @Transactional
    public String updateNews(@RequestBody News news) {
        if (!newsService.checkNews(news)) {
            return "请输入新闻信息";
        }
        try {
            UpdateWrapper<News> newsUpdateWrapper = new UpdateWrapper<>();
            newsUpdateWrapper.eq("id", news.getId());
            boolean res = newsService.update(news, newsUpdateWrapper);
            if (!res) {
                return "新闻更新失败，请重新尝试";
            }
        } catch (Exception e) {
            return "新闻内容不能为空";
        }
        return "新闻更新成功";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteNews(@RequestParam("newsId") Integer newsId) {
        if (newsId == null) {
            return "新闻删除失败，请重新尝试";
        }
        try {
            boolean res = newsService.removeById(newsId);
            if (!res) {
                return "新闻删除失败，请重新尝试";
            }
        } catch (Exception e) {
            return "新闻删除失败，请重新尝试";
        }
        return "新闻删除成功";
    }

}
