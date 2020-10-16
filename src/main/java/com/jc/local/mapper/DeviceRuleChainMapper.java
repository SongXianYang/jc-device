package com.jc.local.mapper;

import com.jc.local.entity.DeviceRuleChain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//设备规则
@Mapper
public interface DeviceRuleChainMapper {
    //查询所有设备规则
    List<DeviceRuleChain> selectAll();
    //根据id查询单个规则
    DeviceRuleChain getById(Integer id);
    //添加设备规则
    int save(DeviceRuleChain deviceRuleChain);
    //删除设备规则
    int deleteId(Integer id);
    //更新设备规则
    int update(DeviceRuleChain deviceRuleChain);
    //根据设备编号查询
    DeviceRuleChain selectNumber(String deviceNum);
}
