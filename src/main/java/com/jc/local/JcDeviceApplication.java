package com.jc.local;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.jc.local.mapper")
public class JcDeviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(JcDeviceApplication.class, args);
    }
}
