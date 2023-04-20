package com.filmmanage.pojo;

import java.util.ArrayList;

import lombok.Data;

/**
 * @Date 2023/2/5
 * @Author zcy Description:
 */
@Data
public class NewsPage {
    long total;
    long size;
    long current;
    private ArrayList<News> records;
}
