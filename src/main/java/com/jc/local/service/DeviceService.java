package com.jc.local.service;

import com.jc.local.dto.ChainNumDTO;
import com.jc.local.entity.Device;

import java.util.List;

public interface DeviceService {
    List<Device> selectAll();

    void state(Integer id, String started);

    //添加设备
    int save(Device device);

    //根据设备名称查询多条规则链
    List<ChainNumDTO> deviceNameJoinChainNumList(String deviceName);

    //根据设备编号删除设备及该设备输出表数据和参数表数据
    int deleteDeviceNumberJoinOutputJoinParam(String deviceNumber);
}
