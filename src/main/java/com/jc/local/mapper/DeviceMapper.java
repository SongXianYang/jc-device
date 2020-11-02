package com.jc.local.mapper;

import com.jc.local.dto.ChainNumDTO;
import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceOutput;
import com.jc.local.entity.DeviceParam;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

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

    /**
     * ids删除多个
     * 用List来接受参数，因为会传来多个 以逗号（，）隔开的字符串整数
     * @param ids
     * @return
     */
    int deleteIds(List<Integer> ids);

    //设备启停
    void startStop(Device device);

    //根据设备编号查询关联的参数表与输出表
    List<Device> numberJoinOutPutJoinParamList(String number);

    List<DeviceOutput> selectOutput(String number);

    List<DeviceParam> selectParam(String number);

    //逻辑删除
    int idDelete(Integer id);

    //根据设备名称查询多条规则链
    List<ChainNumDTO> deviceNameJoinChainNumList(String deviceName);

    //根据设备编号删除设备及该设备输出表数据和参数表数据
    int deleteDeviceNumberJoinOutputJoinParam(String deviceNumber);

    //利用MySQL数据库limit进行分页
    List<Device> limitFindAll (Map<String,Integer> map);
}
