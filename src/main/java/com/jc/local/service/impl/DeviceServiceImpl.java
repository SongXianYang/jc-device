package com.jc.local.service.impl;

import com.jc.local.entity.Device;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    DeviceMapper deviceMapper;

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


}
