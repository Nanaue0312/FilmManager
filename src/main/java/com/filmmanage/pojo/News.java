package com.filmmanage.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * 新闻表
 */
@Data
public class News implements Serializable {
    private static final long serialVersionUID = -2091130973388854193L;
    /**
     * 新闻id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布日期
     */
    private String releaseDate;

    /**
     * 内容
     */
    private String content;
}
