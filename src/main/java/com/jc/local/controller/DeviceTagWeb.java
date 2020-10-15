package com.jc.local.controller;

import com.jc.local.entity.DeviceTag;
import com.jc.local.mapper.DeviceTagMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//设备标签
@RestController
@RequestMapping("DeviceTag")
public class DeviceTagWeb {
    @Autowired
    DeviceTagMapper deviceTagMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备标签", notes = "查询所有设备标签")
    public List<DeviceTag> list() {
        List<DeviceTag> list = deviceTagMapper.selectAll();
        return list;
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备标签id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备标签", notes = "根据id删除设备标签")
    public String deleteId(@PathVariable int id) {
        int result = deviceTagMapper.deleteId(id);
        if (result >= 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备标签", notes = "添加设备标签")
    public String save(DeviceTag deviceTag) {
        int result = deviceTagMapper.save(deviceTag);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备标签", notes = "更新设备标签")
    public String update(DeviceTag deviceTag) {
        int result = deviceTagMapper.update(deviceTag);
        if (result >= 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备标签", notes = "根据id查询设备标签")
    @ApiImplicitParam(name = "id", value = "设备标签id", dataType = "int")
    public DeviceTag getById(@PathVariable Integer id) {
        DeviceTag byId = deviceTagMapper.getById(id);
        return byId;
    }

}
