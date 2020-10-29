package com.jc.local.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    //分页查询设备每页显示2条数据
    PageInfo<Device> pageFindAll(int pageNum, int pageSize);

    //利用MySQL数据库limit进行分页每页显示两条数据
    List<Device> limitFindAll(int pageNum,int pageSize);
}
