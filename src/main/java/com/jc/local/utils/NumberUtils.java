package com.jc.local.utils;

import java.util.Random;

public class NumberUtils {
    //生成随机的设备编号
    public static synchronized String createUniqueKey() {
        Random random = new Random();//生成随机数
        int key = random.nextInt(90000) + 10000;   //生成5位随机数
        return System.currentTimeMillis() + String.valueOf(key);   //返回把毫秒带上防止重复
    }
}
