package com.jc.local.service;

import com.jc.local.utils.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;

@SpringBootTest
class DeviceServiceTest {
    @Autowired
    private DeviceService deviceService;


    @Test
    void keys() {
        String key = NumberUtils.createNumberKey();
        System.out.println(key);
        System.out.println(key);
        System.out.println(key);
    }
}