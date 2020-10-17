package com.jc.local.controller;

import com.jc.local.entity.*;
import com.jc.local.mapper.*;
import com.jc.local.service.DeviceService;
import com.jc.local.utils.NumberUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("device")
@Log
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    DeviceRuleChainMapper deviceRuleChainMapper;
    @Autowired
    DeviceParamMapper deviceParamMapper;
    @Autowired
    DeviceOutputMapper deviceOutputMapper;
    @Autowired
    DeviceGroupMapper deviceGroupMapper;
    @Autowired
    DeviceTagMapper deviceTagMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备", notes = "查询所有设备")
    public List<Device> list() {
        List<Device> list = deviceService.selectAll();
        return list;
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备", notes = "根据id删除设备")
    public String deleteId(@PathVariable int id) {
        int result = deviceMapper.deleteId(id);
        if (result >= 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备", notes = "添加设备")
    public String save(Device device) {
        String number = NumberUtils.createNumberKey();
        device.setNumber(number);

        DeviceRuleChain deviceRuleChain = new DeviceRuleChain();
        deviceRuleChain.setDeviceNum(number);
        deviceRuleChainMapper.save(deviceRuleChain);

        DeviceParam deviceParam = new DeviceParam();
        deviceParam.setDeviceNum(number);
        deviceParamMapper.save(deviceParam);

        DeviceOutput deviceOutput = new DeviceOutput();
        deviceOutput.setDeviceNum(number);
        deviceOutputMapper.save(deviceOutput);

//        DeviceGroup deviceGroup = new DeviceGroup();
//        deviceGroup.setDeviceNum(number);
//        deviceGroupMapper.save(deviceGroup);

        DeviceTag deviceTag = new DeviceTag();
        deviceTag.setDeviceNum(number);
        deviceTagMapper.save(deviceTag);

        int result = deviceMapper.save(device);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备", notes = "更新设备")
//    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public String update(Device device) {
//        device=deviceMapper.getById(id);
        int result = deviceMapper.update(device);
        if (result >= 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备", notes = "根据id查询设备")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public Device getById(@PathVariable Integer id) {
        Device byId = deviceMapper.getById(id);
        return byId;
    }

    @DeleteMapping("deleteIds/{ids}")
    @ApiOperation(value = "根据ids进行批量删除", notes = "根据ids进行批量删除")
    @ApiImplicitParam(name = "ids", value = "设备ids", dataType = "List<Integer>")
    public String deleteIds(@PathVariable("ids") String pdId) {
        String[] pdIds = pdId.split(",");
        ArrayList<Integer> ids = new ArrayList<>();
        for (String str : pdIds) {
            ids.add(Integer.parseInt(str));
        }
        int result = deviceMapper.deleteIds(ids);
        if (result >= 1) {
            return "批量删除成功！！";
        } else {
            return "批量删除失败";
        }
    }

    @PutMapping("start/{id}")
    @ApiOperation(value = "设备启动", notes = "设备启动")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public String start(@PathVariable int id) {
//        Device deviceStop = deviceMapper.getById(id);
//        if (deviceStop.getStatus().equals(1)) {
//            deviceStop.setStatus("0");
//            deviceMapper.startStop(deviceStop);
//            System.out.println(deviceStop);
//            return "设备关闭成功";
//        } else {
//            Device deviceStart = new Device();
//            deviceStart.setStatus("1");
//            deviceMapper.startStop(deviceStart);
//            return "设备开启成功";
//        }
        deviceService.state(id, "1");
        return "启动成功";
    }

    @PutMapping("stop/{id}")
    @ApiOperation(value = "设备停止", notes = "设备停止")
    @ApiImplicitParam(name = "id", value = "停止设备id", dataType = "int")
    public String stop(@PathVariable int id) {
        deviceService.state(id, "0");
        return "停止成功";
    }

    //关联设备输出 （一对一）
    @GetMapping("/deviceJoinDeviceOutput/{number}")
    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
    @ApiOperation(value = "设备表与输出表 通过设备编号进行关联的数据", notes = "设备表与输出表 通过设备编号进行关联的数据")
    public List<Device> deviceJoinDeviceOutput(@PathVariable String number) {
        List<Device> list = deviceMapper.deviceJoinDeviceOutput(number);
        return list;
    }

    //关联设备参数 （一对多）
    @GetMapping("/deviceJoinDeviceParamList/{number}")
    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
    @ApiOperation(value = "设备表与参数表 通过设备编号进行关联的数据", notes = "设备表与参数表 通过设备编号进行关联的数据")
    public List<Device> deviceJoinDeviceParamList (@PathVariable String number) {
        List<Device> list = deviceMapper.deviceJoinDeviceParamList(number);
        return list;
    }
    //一台设备对一个规则
    @GetMapping("/deviceJoinDeviceRule/{number}")
    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
    @ApiOperation(value = "设备表与规则表 通过设备编号进行关联的数据", notes = "设备表与规则表 通过设备编号进行关联的数据")
    public Device deviceJoinDeviceRule (@PathVariable String number) {
        log.config("他被调用了");
        Device device = deviceMapper.deviceJoinDeviceRule(number);
        return device;
    }
}
