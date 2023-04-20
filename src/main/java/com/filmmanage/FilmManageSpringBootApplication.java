package com.filmmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.filmmanage.mapper")
public class FilmManageSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmManageSpringBootApplication.class, args);
    }

}
