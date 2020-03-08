package com.lewic.todoscore.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenParam = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        //header中的token参数非必填，传空也可以
        tokenParam.name("token").description("Auth token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        //添加公共参数
        params.add(tokenParam.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.lewic.todoscore"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(params)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("任务打卡")
                .description("测试服务RESTFUL")
                .termsOfServiceUrl("https://blog.csdn.net/weixin_43290368")
                .version("1.0")
                .build();

//        return new ApiInfo(appName, appDesc, appVersion, termsOfServiceUrl,
//                new Contact(contactName, contactUrl, contactEmail),
//                license, licenseUrl);
    }
}
