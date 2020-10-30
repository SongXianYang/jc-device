package com.jc.local.mapper;

import com.jc.local.entity.DeviceGroup;
import com.jc.local.dto.GroupAndDeviceDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
设备分组
 */
@Mapper
public interface DeviceGroupMapper {
    //查询所有设备
    List<DeviceGroup> selectAll();

    //根据id查询单个用户
    DeviceGroup getById(Integer id);

    @Insert("insert into device_group (ID, DEVICE_NUM, GROUP_NUM, \n" +
            "      IS_DEL, OP_FLAG, CREATED_BY, \n" +
            "      CREATED_TIME, UPDATED_BY, UPDATED_TIME\n" +
            "      )\n" +
            "    values (#{id,jdbcType=INTEGER}, #{deviceNum,jdbcType=VARCHAR}, #{groupNum,jdbcType=VARCHAR}, \n" +
            "      #{isDel,jdbcType=CHAR}, #{opFlag,jdbcType=CHAR}, #{createdBy,jdbcType=VARCHAR}, \n" +
            "      #{createdTime,jdbcType=TIMESTAMP}, #{updatedBy,jdbcType=VARCHAR}, #{updatedTime,jdbcType=TIMESTAMP}\n" +
            "      )")
        //添加设备
    int save(DeviceGroup deviceGroup);

    //删除设备

    /**
     *
     * @param id
     * @return
     */
    @Delete("delete from device_group where ID=#{id} ")
    int deleteId(Integer id);

    //更新设备
    int update(DeviceGroup deviceGroup);

}
