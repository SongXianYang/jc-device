package com.jc.local.service;

import com.jc.local.entity.Device;


import java.util.List;

public interface DeviceService {
    List<Device> selectAll();
    void state(Integer id,String started);
    //添加设备
    int save(Device device);

    String insertAll();
}
