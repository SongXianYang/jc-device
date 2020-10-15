package com.jc.local.controller;

import com.jc.local.entity.Device;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.service.DeviceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    DeviceMapper deviceMapper;

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
}
