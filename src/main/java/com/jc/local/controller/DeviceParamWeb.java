package com.jc.local.controller;

import com.jc.local.entity.DeviceParam;
import com.jc.local.mapper.DeviceParamMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//设备参数
@RestController
@RequestMapping("DeviceParam")
public class DeviceParamWeb {
    @Autowired
    DeviceParamMapper deviceParamMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备参数", notes = "查询所有设备参数")
    public List<DeviceParam> list() {
        List<DeviceParam> list = deviceParamMapper.selectAll();
        return list;
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备参数id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备参数", notes = "根据id删除设备参数")
    public String deleteId(@PathVariable int id) {
        int result = deviceParamMapper.deleteId(id);
        if (result >= 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备参数", notes = "添加设备参数")
    public String save(DeviceParam deviceParam) {
        int result = deviceParamMapper.save(deviceParam);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备参数", notes = "更新设备参数")
    public String update(DeviceParam deviceParam) {
        int result = deviceParamMapper.update(deviceParam);
        if (result >= 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备参数", notes = "根据id查询设备参数")
    @ApiImplicitParam(name = "id", value = "设备参数id", dataType = "int")
    public DeviceParam getById(@PathVariable Integer id) {
        DeviceParam byId = deviceParamMapper.getById(id);
        return byId;
    }

}
