package com.jc.local.config;

import com.xzixi.swagger2.plus.annotation.EnableSwagger2Plus;
import org.springframework.boot.SpringBootConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootConfiguration
@EnableSwagger2Plus
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())        //这个方法的作用（生成接口的时候页面显示的信息）
                .select()         //表示的是选择那些路径和API生成文档
                .apis(RequestHandlerSelectors.basePackage("com.jc.local.controller")) //告诉他要扫描的接口存在的这个包
                .paths(PathSelectors.any())          //对所有的API进行监控
                .build();         //构建
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("这里是测试物联网设备的功能文档")    //文档的标题
                .description("测试用的")      //文档的描述
                .version("v0.0.1")                          //版本
                .build();
    }
}

