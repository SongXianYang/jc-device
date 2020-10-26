package com.jc.local.controller;

import com.jc.local.entity.DeviceTag;
import com.jc.local.mapper.DeviceTagMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//设备标签
@Api(tags = "设备标签接口")
@Slf4j
@RestController
@RequestMapping("DeviceTag")
public class DeviceTagWeb {
    DeviceTagMapper deviceTagMapper;

    public DeviceTagWeb(DeviceTagMapper deviceTagMapper) {
        this.deviceTagMapper = deviceTagMapper;
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备标签", notes = "查询所有设备标签")
    public List<DeviceTag> list() {
        try {
            List<DeviceTag> list = deviceTagMapper.selectAll();
            return list;
        } catch (Exception exception) {
            log.error("查询所有设备标签", exception);
            throw exception;
        }
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备标签id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备标签", notes = "根据id删除设备标签")
    public String deleteId(@PathVariable int id) {
        try {
            int result = deviceTagMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "删除失败！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备标签", exception);
            throw exception;
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备标签", notes = "添加设备标签")
    public String save(DeviceTag deviceTag) {
        try {
            int result = deviceTagMapper.save(deviceTag);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加设备标签", exception);
            throw exception;
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备标签", notes = "更新设备标签")
    public String update(DeviceTag deviceTag) {
        try {
            int result = deviceTagMapper.update(deviceTag);
            if (result >= 1) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } catch (Exception exception) {
            log.error("更新设备标签", exception);
            throw exception;
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备标签", notes = "根据id查询设备标签")
    @ApiImplicitParam(name = "id", value = "设备标签id", dataType = "int")
    public DeviceTag getById(@PathVariable Integer id) {
        try {
            DeviceTag byId = deviceTagMapper.getById(id);
            return byId;
        } catch (Exception exception) {
            log.error("根据id查询设备标签", exception);
            throw exception;
        }
    }

}
