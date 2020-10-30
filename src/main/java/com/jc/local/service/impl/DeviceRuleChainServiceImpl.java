package com.jc.local.service.impl;

import com.jc.local.entity.DeviceRuleChain;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.DeviceRuleChainMapper;
import com.jc.local.service.DeviceRuleChainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DeviceRuleChainServiceImpl implements DeviceRuleChainService {
    DeviceRuleChainMapper deviceRuleChainMapper;
    HttpAPIService httpAPIService;
    DeviceMapper deviceMapper;

    public DeviceRuleChainServiceImpl(DeviceRuleChainMapper deviceRuleChainMapper, HttpAPIService httpAPIService, DeviceMapper deviceMapper) {
        this.deviceRuleChainMapper = deviceRuleChainMapper;
        this.httpAPIService = httpAPIService;
        this.deviceMapper = deviceMapper;
    }
    @Override
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public int save(DeviceRuleChain deviceRuleChain) {
        return deviceRuleChainMapper.save(deviceRuleChain);
    }
}
