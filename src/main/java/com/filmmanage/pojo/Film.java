package com.filmmanage.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * 电影表
 */
@Data
public class Film implements Serializable {

    private static final long serialVersionUID = -345163701964938560L;
    /**
     * 电影id
     */
    private Integer id;
    /**
     * 电影名
     */
    private String filmName;
    /**
     * 导演
     */
    private String director;
    /**
     * 演员
     */
    private String actor;
    /**
     * 国家/地区
     */
    private String area;
    /**
     * 电影类型
     */
    private String filmType;
    /**
     * 发行日期
     */
    private String releaseDate;
}
