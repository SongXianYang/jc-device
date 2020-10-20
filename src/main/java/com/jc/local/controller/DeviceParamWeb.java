package com.jc.local.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceParam;
import com.jc.local.entity.devRepo.ModelParam;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.DeviceParamMapper;
import com.jc.local.service.DeviceParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
//设备参数
@RestController
@RequestMapping("DeviceParam")
@Api(tags = "设备参数接口")
public class DeviceParamWeb {

    DeviceParamMapper deviceParamMapper;

    HttpAPIService httpAPIService;

    DeviceParamService deviceParamService;

    DeviceMapper deviceMapper;

    public DeviceParamWeb(DeviceParamMapper deviceParamMapper, DeviceParamService deviceParamService, HttpAPIService httpAPIService, DeviceMapper deviceMapper) {
        this.deviceParamMapper = deviceParamMapper;
        this.deviceParamService = deviceParamService;
        this.httpAPIService = httpAPIService;
        this.deviceMapper = deviceMapper;
    }

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //修改日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

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

    @PostMapping("save/{pid}/{did}")
    @ApiOperation(value = "添加设备参数", notes = "添加设备参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "参数id", dataType = "Integer"),
            @ApiImplicitParam(name = "did", value = "设备id", dataType = "Integer")
    })
    public String save(@PathVariable Integer pid,@PathVariable Integer did) {
        DeviceParam deviceParam = new DeviceParam();
        try {
            String s = httpAPIService.doGet("http://192.168.0.25:8888/modelparam/selectOne/" + pid);
            ModelParam modelParam = mapper.readValue(s, ModelParam.class);
            Device device = deviceMapper.getById(did);
            deviceParam.setDeviceNum(device.getNumber());
            deviceParam.setParamNum(modelParam.getNumber());
            deviceParam.setCode(modelParam.getCode());
            deviceParam.setValue(modelParam.getMDefault());
        } catch (Exception exception) {
            exception.printStackTrace();
        }


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
