package com.jc.local.service.impl;

import com.jc.local.entity.DeviceTag;
import com.jc.local.mapper.DeviceTagMapper;
import com.jc.local.service.DeviceTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: SongXianYang
 * @create: 2020-10-27 11:29
 **/
@Service
public class DeviceTagServiceImpl implements DeviceTagService {

    DeviceTagMapper deviceTagMapper;

    public DeviceTagServiceImpl(DeviceTagMapper deviceTagMapper) {
        this.deviceTagMapper = deviceTagMapper;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int save(DeviceTag deviceTag) {
        return deviceTagMapper.save(deviceTag);
    }
}
