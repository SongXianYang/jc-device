package com.jc.local.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceOutput;
import com.jc.local.entity.devRepo.Model;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.service.DeviceService;
import com.jc.local.utils.NumberUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    HttpAPIService httpAPIService;
    DeviceMapper deviceMapper;
    public DeviceServiceImpl(DeviceMapper deviceMapper,HttpAPIService httpAPIService) {
        this.deviceMapper = deviceMapper;
        this.httpAPIService = httpAPIService;
    }

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //修改日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public List<Device> selectAll() {
        return deviceMapper.selectAll();
    }

    @Override
    public void state(Integer id, String started) {
        Device device = deviceMapper.getById(id);
        device.setStatus(started);
        deviceMapper.startStop( device );
    }

    @Override
    public int save(Device device) {
        return deviceMapper.save(device);
    }

    @Override
    public String insertAll() {
        return null;
    }


}
