package com.jc.local.service;

import com.jc.local.entity.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DeviceServiceTest {
    @Autowired
    private DeviceService deviceService;

    @Test
    private List<Device> selectAll() {
        return deviceService.selectAll();
    }
}