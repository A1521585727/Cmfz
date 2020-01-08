package com.baizhi.rlt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.baizhi.rlt.dao")
public class CmfzApplication /*extends SpringBootServletInitializer */{

    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class, args);
    }

//    public SpringApplicationBuilder cafigure(){
//
//    }
}
