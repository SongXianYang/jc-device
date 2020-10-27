package com.jc.local.service.impl;

import com.jc.local.entity.DeviceGroup;
import com.jc.local.entity.DeviceOutput;
import com.jc.local.mapper.DeviceGroupMapper;
import com.jc.local.service.DeviceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceGroupServiceImpl implements DeviceGroupService {

    DeviceGroupMapper deviceGroupMapper;

    public DeviceGroupServiceImpl(DeviceGroupMapper deviceGroupMapper) {
        this.deviceGroupMapper = deviceGroupMapper;
    }

    @Override
    public List<DeviceGroup> selectAll() {
        List<DeviceGroup> list = deviceGroupMapper.selectAll();
        return list;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int save(DeviceGroup deviceGroup) {
        return deviceGroupMapper.save(deviceGroup);
    }
}
