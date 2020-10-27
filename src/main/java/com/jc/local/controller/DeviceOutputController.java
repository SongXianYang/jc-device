package com.jc.local.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceOutput;
import com.jc.local.entity.devRepo.ModelOutput;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.DeviceOutputMapper;
import com.jc.local.service.DeviceOutputService;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

//设备输出
@Api(tags = "设备输出")
@Slf4j
@RestController
@RequestMapping("DeviceOutput")
public class DeviceOutputController {

    DeviceOutputMapper deviceOutputMapper;

    DeviceOutputService deviceOutputService;

    HttpAPIService httpAPIService;

    DeviceMapper deviceMapper;

    public DeviceOutputController(DeviceOutputService deviceOutputService, DeviceOutputMapper deviceOutputMapper, HttpAPIService httpAPIService, DeviceMapper deviceMapper) {
        this.deviceOutputService = deviceOutputService;
        this.deviceOutputMapper = deviceOutputMapper;
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

    /**
     * 查询所有设备输出
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "查询所有设备输出", notes = "查询所有设备输出")
    public Response<List<DeviceOutput>> selectAll() {
        try {
            List<DeviceOutput> list = deviceOutputMapper.selectAll();
            return Response.success(list);
        } catch (Exception exception) {
            log.error("查询所有设备输出", exception);
            throw exception;
        }
    }

    /**
     * 根据id删除设备输出
     * @param id
     * @return
     */
    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id",value = "设备id",required =true,dataType = "int")
    @ApiOperation(value = "根据id删除设备输出", notes = "根据id删除设备输出")
    public String deleteId(@PathVariable int id) {
        try {
            int result = deviceOutputMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "删除失败！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备输出", exception);
            throw exception;
        }
    }

    /**
     * 添加设备输出
     * @param oid
     * @param did
     * @return
     */
    @PostMapping("save/{oid}/{did}")
    @ApiOperation(value = "添加设备输出", notes = "添加设备输出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oid", value = "输出表id", dataType = "Integer"),
            @ApiImplicitParam(name = "did", value = "设备id", dataType = "Integer")
    })
    public String save(@PathVariable Integer oid, @PathVariable Integer did) {
        DeviceOutput deviceOutput = new DeviceOutput();

        try {
            try {
                String s = httpAPIService.doGet("http://192.168.0.25:8888/modeloutput/selectOne/"+oid);
                ModelOutput modelOutput = mapper.readValue(s, ModelOutput.class);
                Device device = deviceMapper.getById(did);
                deviceOutput.setDeviceNum(device.getNumber());
                deviceOutput.setMetaNum(modelOutput.getNumber());
                deviceOutput.setCode(modelOutput.getOutputCode());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            int result = deviceOutputMapper.save(deviceOutput);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加设备输出", exception);
            throw exception;
        }
    }

    /**
     * 更新设备输出
     * @param deviceOutput
     * @return
     */
    @PutMapping("update")
    @ApiOperation(value = "更新设备输出", notes = "更新设备输出")
    public String update(DeviceOutput deviceOutput) {
        try {
            int result = deviceOutputMapper.update(deviceOutput);
            if (result >= 1) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } catch (Exception exception) {
            log.error("更新设备输出", exception);
            throw exception;
        }
    }

    /**
     * 根据id查询设备输出
     * @param id
     * @return
     */
    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备输出", notes = "根据id查询设备输出")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public Response<DeviceOutput> getById(@PathVariable Integer id) {
        try {
            DeviceOutput byId = deviceOutputMapper.getById(id);
            return Response.success(byId);
        } catch (Exception exception) {
            log.error("根据id查询设备输出", exception);
            throw exception;
        }
    }
}
