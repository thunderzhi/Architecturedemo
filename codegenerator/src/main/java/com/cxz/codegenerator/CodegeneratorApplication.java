package com.cxz.codegenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cxz.mapper")
public class CodegeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodegeneratorApplication.class, args);
    }

}
