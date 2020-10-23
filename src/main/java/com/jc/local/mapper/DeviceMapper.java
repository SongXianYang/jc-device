package com.jc.local.mapper;

import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceOutput;
import com.jc.local.entity.DeviceParam;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

//设备
@Mapper
public interface DeviceMapper {
    //查询所有设备
    List<Device> selectAll();

    //根据id查询单个用户
    Device getById(Integer id);

    //添加设备
    int save(Device device);

    //删除设备
    int deleteId(Integer id);

    //更新设备
    int update(Device device);

    //ids删除多个
    int deleteIds(List<Integer> ids);

    //设备启停
    void startStop(Device device);

    //根据设备编号查询关联的参数表与输出表
    List<Device> numberJoinOutPutJoinParamList(String number);
    List<DeviceOutput> selectOutput(String number);
    List<DeviceParam> selectParam(String number);
    //逻辑删除
    int idDelete(Integer id);
}
