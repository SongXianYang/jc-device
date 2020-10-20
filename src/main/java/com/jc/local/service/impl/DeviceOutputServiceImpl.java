package com.jc.local.service.impl;

import com.jc.local.entity.DeviceOutput;
import com.jc.local.mapper.DeviceOutputMapper;
import com.jc.local.service.DeviceOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceOutputServiceImpl implements DeviceOutputService {
    DeviceOutputMapper deviceOutputMapper;

    public DeviceOutputServiceImpl(DeviceOutputMapper deviceOutputMapper) {
        this.deviceOutputMapper = deviceOutputMapper;
    }

    @Override
    public int save(DeviceOutput deviceOutput) {
        return deviceOutputMapper.save(deviceOutput);
    }
}
