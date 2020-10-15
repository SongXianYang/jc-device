package com.jc.local.mapper;

import com.jc.local.entity.DeviceTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//设备标签
@Mapper
public interface DeviceTagMapper {
    //查询所有设备标签
    List<DeviceTag> selectAll();
    //根据id查询单个设备标签
    DeviceTag getById(Integer id);
    //添加设备标签
    int save(DeviceTag deviceTag);
    //删除设备标签
    int deleteId(Integer id);
    //更新设备标签
    int update(DeviceTag deviceTag);
}
