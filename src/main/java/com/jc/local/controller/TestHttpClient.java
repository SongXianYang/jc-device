package com.jc.local.controller;

import com.jc.local.http.HttpAPIService;
import com.jc.local.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/test")
public class TestHttpClient {
    @Autowired
    private HttpAPIService httpAPIService;
    @Autowired
    DeviceService deviceService;

    @GetMapping("http")
    public String test() {
        try {
            String str = httpAPIService.doGet("https://www.baidu.com");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "你好HttpClient";
    }
}
