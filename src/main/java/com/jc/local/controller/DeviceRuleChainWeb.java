package com.jc.local.controller;

import com.jc.local.entity.DeviceRuleChain;
import com.jc.local.mapper.DeviceRuleChainMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//设备规则链链
@RestController
@RequestMapping("DeviceRuleChain")
public class DeviceRuleChainWeb {
    @Autowired
    DeviceRuleChainMapper deviceRuleChainMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备规则链", notes = "查询所有设备规则链")
    public List<DeviceRuleChain> list() {
        List<DeviceRuleChain> list = deviceRuleChainMapper.selectAll();
        return list;
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备规则链id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备规则链", notes = "根据id删除设备规则链")
    public String deleteId(@PathVariable int id) {
        int result = deviceRuleChainMapper.deleteId(id);
        if (result >= 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备规则链", notes = "添加设备规则链")
    public String save(DeviceRuleChain deviceRuleChain) {
        int result = deviceRuleChainMapper.save(deviceRuleChain);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备规则链", notes = "更新设备规则链")
    public String update(DeviceRuleChain deviceRuleChain) {
        int result = deviceRuleChainMapper.update(deviceRuleChain);
        if (result >= 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备规则链", notes = "根据id查询设备规则链")
    @ApiImplicitParam(name = "id", value = "设备规则链id", dataType = "int")
    public DeviceRuleChain getById(@PathVariable Integer id) {
        DeviceRuleChain byId = deviceRuleChainMapper.getById(id);
        return byId;
    }

}
