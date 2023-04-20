package com.filmmanage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.filmmanage.pojo.Film;
import com.filmmanage.service.FilmService;
import com.filmmanage.service.impl.PageServiceImpl;

/**
 * @author Zcy
 * @createTime 2022/12/31 20:36
 * @description
 */
@Controller
@RequestMapping("/film")
public class FilmController {
    @Autowired
    FilmService filmService;

    // @GetMapping("/films/{pageNo}")
    // @ResponseBody
    // public String getFilms(@PathVariable("pageNo") Integer pageNo) {
    // System.out.println(pageNo);
    // return "查询电影列表";
    // }

    @PostMapping("/saveFilm")
    @ResponseBody
    @Transactional
    public String saveFilm(@RequestBody Film film) {
        if (film == null) {
            return "请输入电影信息";
        }
        try {
            boolean res = filmService.save(film);
            if (!res) {
                return "电影保存失败，请重新尝试";
            }
        } catch (Exception e) {
            return "电影已存在，请不要重复保存";
        }
        return "电影保存成功";
    }

    @GetMapping("/film_info/{current}")
    public String selectFilms(@PathVariable("current") Integer current, HttpServletRequest request) {
        Page<Film> filmPage = new Page<>();
        filmPage.setCurrent(current);
        Page<Film> page = filmService.page(filmPage, null);
        int[] pageNav = PageServiceImpl.pageNav(current, (int)page.getPages(), 3);
        request.setAttribute("records", page.getRecords());
        request.setAttribute("hasPrevious", page.hasPrevious());
        request.setAttribute("hasNext", page.hasNext());
        request.setAttribute("current", page.getCurrent());
        request.setAttribute("pages", page.getPages());
        request.setAttribute("navigation", pageNav);
        // return page;
        return "film/film_list";
    }

    @GetMapping("toUpdate/{filmId}")
    public String updateNews(@PathVariable("filmId") Integer filmId, Model model) {
        Film film = filmService.getById(filmId);
        model.addAttribute("film", film);
        return "film/update_film";
    }

    @PostMapping("/updateFilm")
    @ResponseBody
    @Transactional
    public String updateFilm(@RequestBody Film film) {
        if (film == null) {
            return "请输入电影信息";
        }
        try {
            UpdateWrapper<Film> filmUpdateWrapper = new UpdateWrapper<>();
            filmUpdateWrapper.eq("id", film.getId());
            boolean res = filmService.update(film, filmUpdateWrapper);
            if (!res) {
                return "电影更新失败，请重新尝试";
            }
        } catch (Exception e) {
            return "电影内容不能为空";
        }
        return "电影更新成功";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteFilm(@RequestParam("filmId") Integer filmId) {
        if (filmId == null) {
            return "电影删除失败，请重新尝试";
        }
        try {
            boolean res = filmService.removeById(filmId);
            if (!res) {
                return "电影删除失败，请重新尝试";
            }
        } catch (Exception e) {
            return "电影删除失败，请重新尝试";
        }
        return "电影删除成功";
    }
}
