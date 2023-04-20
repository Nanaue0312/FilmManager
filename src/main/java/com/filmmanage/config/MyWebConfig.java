package com.filmmanage.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.filmmanage.interceptor.MyInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @Date 2023/2/3
 * @Author zcy Description:
 */
@Configuration
@Slf4j
public class MyWebConfig implements WebMvcConfigurer {
    /**
     * 自定义视图控制器
     *
     * @param registry
     * @see ViewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/").setViewName("/user/login.html");
        registry.addViewController("/user/toLogin").setViewName("user/login.html");
        registry.addViewController("/user/toRegister").setViewName("user/register.html");
        registry.addViewController("/user/toUpdate").setViewName("user/user_info_update.html");
        registry.addViewController("/film/toAddFilm").setViewName("film/add_film.html");
        registry.addViewController("/news/toAddNews").setViewName("news/add_news.html");
        registry.addViewController("/news/news_info").setViewName("news/news_list.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> exPathPatterns = new ArrayList<>();
        exPathPatterns.add("/css/**");
        exPathPatterns.add("/js/**");
        exPathPatterns.add("/images/**");
        exPathPatterns.add("/layui/**");
        exPathPatterns.add("/user/toLogin");
        exPathPatterns.add("/user/toRegister");
        exPathPatterns.add("/favicon.ico");
        exPathPatterns.add("/user/login");
        exPathPatterns.add("/user/register");
        exPathPatterns.add("/error");
        exPathPatterns.add("/user/checkUserAccount/**");
        exPathPatterns.add("/film/saveFilm");
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns(exPathPatterns);
    }
}
