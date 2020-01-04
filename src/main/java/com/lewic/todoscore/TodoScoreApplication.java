package com.lewic.todoscore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.lewic.todoscore.dao")
@SpringBootApplication
@EnableSwagger2
public class TodoScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoScoreApplication.class, args);
    }

}
