package com.jc.local.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.entity.*;
import com.jc.local.entity.devRepo.Model;
import com.jc.local.entity.ruleEntity.Chain;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.*;
import com.jc.local.service.DeviceService;
import com.jc.local.utils.NumberUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("device")
@Api(tags = "设备接口")
public class DeviceController {

    DeviceService deviceService;

    DeviceMapper deviceMapper;

    DeviceRuleChainMapper deviceRuleChainMapper;

    DeviceParamMapper deviceParamMapper;

    DeviceOutputMapper deviceOutputMapper;

    DeviceGroupMapper deviceGroupMapper;

    DeviceTagMapper deviceTagMapper;

    HttpAPIService httpAPIService;

    public DeviceController(DeviceService deviceService, DeviceMapper deviceMapper,
                            DeviceRuleChainMapper deviceRuleChainMapper,
                            DeviceParamMapper deviceParamMapper,
                            DeviceOutputMapper deviceOutputMapper,
                            DeviceGroupMapper deviceGroupMapper,
                            DeviceTagMapper deviceTagMapper,
                            HttpAPIService httpAPIService) {
        this.deviceService = deviceService;
        this.deviceMapper = deviceMapper;
        this.deviceRuleChainMapper = deviceRuleChainMapper;
        this.deviceParamMapper = deviceParamMapper;
        this.deviceOutputMapper = deviceOutputMapper;
        this.deviceGroupMapper = deviceGroupMapper;
        this.deviceTagMapper = deviceTagMapper;
        this.httpAPIService = httpAPIService;
    }

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        //修改日期格式
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

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

    @PostMapping("save/{mid}")
    @ApiImplicitParam(name = "mid",value = "型号id",dataType = "int")
    @ApiOperation(value = "添加设备", notes = "添加设备")
    public String save(@PathVariable int mid) {
        Device device = new Device();
        try {
            String number = NumberUtils.createNumberKey();
            device.setNumber(number);
            String s = httpAPIService.doGet("http://192.168.0.25:8888/model/selectOne/" + mid);
            //获取型号编号对象
            Model models = mapper.readValue(s, Model.class);
            System.out.println(models);
            device.setDmNum(models.getNumber());
            device.setName(models.getName());
            device.setDevSn(models.getManuNum()+models.getNumber());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        int result = deviceService.save(device);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备", notes = "更新设备")
    public String update(Device device) {
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
    public List<Device> deviceJoinDeviceParamList(@PathVariable String number) {
        List<Device> list = deviceMapper.deviceJoinDeviceParamList(number);
        return list;
    }

    //一台设备对一个规则
    @GetMapping("/deviceJoinDeviceRule/{number}")
    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
    @ApiOperation(value = "设备表与规则表 通过设备编号进行关联的数据", notes = "设备表与规则表 通过设备编号进行关联的数据")
    public Device deviceJoinDeviceRule(@PathVariable String number) {
        Device device = deviceMapper.deviceJoinDeviceRule(number);
        return device;
    }

    //根据
}
