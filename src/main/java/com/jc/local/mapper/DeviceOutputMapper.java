package com.jc.local.mapper;

import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceOutput;
import com.jc.local.entity.DeviceRuleChain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//设备输出
@Mapper
public interface DeviceOutputMapper {
    //查询所有设备输出
    List<DeviceOutput> selectAll();
    //根据id查询单个设备输出
    DeviceOutput getById(Integer id);
    //添加设备输出
    int save(DeviceOutput deviceOutput);
    //删除设备输出
    int deleteId(Integer id);
    //更新设备输出
    int update(DeviceOutput deviceOutput);
    //根据设备编号查询
    DeviceRuleChain selectNumber(String deviceNum);
}
