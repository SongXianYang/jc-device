package com.jc.local.mapper;


import com.jc.local.entity.DeviceParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//设备参数
@Mapper
public interface DeviceParamMapper {
    //查询所有设备参数
    List<DeviceParam> selectAll();
    //根据id查询单个用户设备参数
    DeviceParam getById(Integer id);
    //添加设备参数
    int save(DeviceParam deviceParam);
    //删除设备参数
    int deleteId(Integer id);
    //更新设备参数
    int update(DeviceParam deviceParam);
}
