package com.xiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  启动类
 *
 * @author Xiang想
 * @date 2020/8/29 10:56
 */
@MapperScan("com.xiang.dao")
@SpringBootApplication
public class ElasticsearchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchDemoApplication.class, args);
    }

}
