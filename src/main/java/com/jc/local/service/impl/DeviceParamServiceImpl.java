package com.jc.local.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.entity.DeviceParam;
import com.jc.local.mapper.DeviceParamMapper;
import com.jc.local.service.DeviceParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@Service
public class DeviceParamServiceImpl implements DeviceParamService {
    public static ObjectMapper mapper = new ObjectMapper();

    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //修改日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    DeviceParamMapper deviceParamMapper;

    public DeviceParamServiceImpl(DeviceParamMapper deviceParamMapper) {
        this.deviceParamMapper = deviceParamMapper;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public int save(DeviceParam deviceParam) {
        return deviceParamMapper.save(deviceParam);
    }
}
