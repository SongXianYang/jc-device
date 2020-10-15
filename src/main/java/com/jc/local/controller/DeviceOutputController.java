package com.jc.local.controller;


import com.jc.local.entity.DeviceOutput;
import com.jc.local.mapper.DeviceOutputMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//设备输出
@RestController
@RequestMapping("DeviceOutput")
public class DeviceOutputController {
    @Autowired
    DeviceOutputMapper deviceOutputMapper;

    @GetMapping("list")
    @ApiOperation(value = "查询所有设备输出", notes = "查询所有设备输出")
    public List<DeviceOutput> selectAll() {
        List<DeviceOutput> list = deviceOutputMapper.selectAll();
        return list;
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id",value = "设备id",required =true,dataType = "int")
    @ApiOperation(value = "根据id删除设备输出", notes = "根据id删除设备输出")
    public String deleteId(@PathVariable int id) {
        int result = deviceOutputMapper.deleteId(id);
        if (result >= 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备输出", notes = "添加设备输出")
    public String save(DeviceOutput deviceOutput) {
        int result = deviceOutputMapper.save(deviceOutput);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备输出", notes = "更新设备输出")
    public String update(DeviceOutput deviceOutput) {
        int result = deviceOutputMapper.update(deviceOutput);
        if (result >= 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备输出", notes = "根据id查询设备输出")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public DeviceOutput getById(@PathVariable Integer id) {
        DeviceOutput byId = deviceOutputMapper.getById(id);
        return byId;
    }
}
