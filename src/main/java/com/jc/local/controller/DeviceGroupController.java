package com.jc.local.controller;

import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceGroup;
import com.jc.local.mapper.DeviceGroupMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//设备分组
@RestController
@RequestMapping("DeviceGroup")
public class DeviceGroupController {
    @Autowired
    DeviceGroupMapper deviceGroupMapper;

    @GetMapping("list")
    @ApiOperation(value = "查询所有设备分组", notes = "查询所有设备分组")
    public List<DeviceGroup> selectAll() {
        List<DeviceGroup> list = deviceGroupMapper.selectAll();
        return list;
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id",value = "设备id",required =true,dataType = "int")
    @ApiOperation(value = "根据id删除设备分组", notes = "根据id删除设备分组")
    public String deleteId(@PathVariable int id) {
        int result = deviceGroupMapper.deleteId(id);
        if (result >= 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备分组", notes = "添加设备分组")
    public String save(DeviceGroup deviceGroup) {
        int result = deviceGroupMapper.save(deviceGroup);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备分组", notes = "更新设备分组")
    public String update(DeviceGroup deviceGroup) {
        int result = deviceGroupMapper.update(deviceGroup);
        if (result >= 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备分组", notes = "根据id查询设备分组")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public DeviceGroup getById(@PathVariable Integer id) {
        DeviceGroup byId = deviceGroupMapper.getById(id);
        return byId;
    }
}
