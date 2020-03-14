package com.lewic.todoscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ServletComponentScan
@SpringBootApplication
@EnableSwagger2
@EnableJpaAuditing
public class TodoScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoScoreApplication.class, args);
    }

}
