package com.jc.local.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.entity.*;
import com.jc.local.entity.devRepo.Model;
import com.jc.local.entity.devRepo.ModelOutput;
import com.jc.local.entity.devRepo.ModelParam;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.*;
import com.jc.local.service.DeviceService;
import com.jc.local.utils.NumberUtils;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        //修改日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

//        TypeFactory typeFactory = mapper.getTypeFactory();
//        CollectionType collectionType = typeFactory.constructCollectionType(
//                List.class, Location.class);
//        InputStream inputStream = new InputStream();
//        return mapper.readValue(inputStream, collectionType);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备", notes = "查询所有设备")
    public Response<List<Device>> list() {
        try {
            List<Device> list = deviceService.selectAll();
            return Response.success(list);
        } catch (Exception exception) {
            log.error("查询所有设备", exception);
            throw exception;
        }
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备", notes = "根据id删除设备")
    public String deleteId(@PathVariable int id) {
        try {
            int result = deviceMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "删除失败！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备", exception);
            throw exception;
        }
    }

//    @PostMapping("save/{mid}")
//    @ApiImplicitParam(name = "mid", value = "设备型号id", dataType = "int")
//    @ApiOperation(value = "添加设备", notes = "添加设备")
//    public String save(@PathVariable int mid) throws Exception {
//        Device device = new Device();
//        try {
//            String number = NumberUtils.createNumberKey();
//            device.setNumber(number);
//            String s = httpAPIService.doGet("http://192.168.0.25:8888/model/selectOne/" + mid);
//            //获取型号编号对象
//            Model models = mapper.readValue(s, Model.class);
//            System.out.println(models);
//            device.setDmNum(models.getNumber());
//            device.setName(models.getName());
//            device.setDevSn(models.getManuNum() + models.getNumber());
//            device.setIsDel("0");
//
//            int result = deviceService.save(device);
//            if (result >= 1) {
//                return "添加成功";
//            } else {
//                return "添加失败";
//            }
//
//        } catch (Exception exception) {
//            log.error("添加设备", exception);
//            throw exception;
//        }
//    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备", notes = "更新设备")
    public String update(Device device) {
        try {
            int result = deviceMapper.update(device);
            if (result >= 1) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } catch (Exception exception) {
            log.error("更新设备", exception);
            throw exception;
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备", notes = "根据id查询设备")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public Response<Device> getById(@PathVariable Integer id) {
        try {
            Device byId = deviceMapper.getById(id);
            return Response.success(byId);
        } catch (Exception exception) {
            log.error("根据id查询设备", exception);
            return Response.failure("根据id查询设备失败");
        }
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

//    //关联设备输出 （一对多）
//    @GetMapping("/deviceJoinDeviceOutput/{number}")
//    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
//    @ApiOperation(value = "设备表与输出表 通过设备编号进行关联的数据", notes = "设备表与输出表 通过设备编号进行关联的数据")
//    public List<Device> deviceJoinDeviceOutputList(@PathVariable String number) {
//        List<Device> list = deviceMapper.deviceJoinDeviceOutputList(number);
//        return list;
//    }
//
//    //关联设备参数 （一对多）
//    @GetMapping("/deviceJoinDeviceParamList/{number}")
//    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
//    @ApiOperation(value = "设备表与参数表 通过设备编号进行关联的数据", notes = "设备表与参数表 通过设备编号进行关联的数据")
//    public List<Device> deviceJoinDeviceParamList(@PathVariable String number) {
//        List<Device> list = deviceMapper.deviceJoinDeviceParamList(number);
//        return list;
//    }
//
//    //一台设备对一个规则
//    @GetMapping("/deviceJoinDeviceRule/{number}")
//    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
//    @ApiOperation(value = "设备表与规则表 通过设备编号进行关联的数据", notes = "设备表与规则表 通过设备编号进行关联的数据")
//    public Device deviceJoinDeviceRule(@PathVariable String number) {
//        Device device = deviceMapper.deviceJoinDeviceRule(number);
//        return device;
//    }

    //物理删除
    @PutMapping("idDelete/{id}")
    @ApiImplicitParam(name = "id", value = "设备编号", dataType = "Integer")
    @ApiOperation(value = "设备逻辑删除", notes = "设备逻辑删除")
    public String idDelete(@PathVariable Integer id) {
        int i = deviceMapper.idDelete(id);
        if (i >= 1) {
            return "逻辑删除成功";
        } else {
            return "逻辑删除失败";
        }
    }

    //添加设备最终版（同时插入设备参数与输出）
    @PostMapping("insertAll/{mid}")
    @ApiImplicitParam(name = "mid", value = "设备型号id", dataType = "int")
    @ApiOperation(value = "添加设备最终版（同时插入设备参数与输出）", notes = "添加设备最终版（同时插入设备参数与输出）")
    public String insertAll(@PathVariable Integer mid) throws Exception {
        Device device = new Device();
        String number = NumberUtils.createNumberKey();
        try {
            device.setNumber(number);
            String s = httpAPIService.doGet("http://192.168.0.25:8888/model/selectOne/" + mid);
            //获取型号编号对象
            Model models = mapper.readValue(s, Model.class);
            device.setDmNum(models.getNumber());
            device.setName(models.getName());
            device.setDevSn(models.getManuNum() + models.getNumber());
            device.setIsDel("0");

            /**
             * 在输出表中插入：设备编号
             */
            DeviceOutput deviceOutput = new DeviceOutput();
            deviceOutput.setDeviceNum(number);
            //利用型号编号查询
            String s1 = httpAPIService
                    .doGet("http://192.168.0.25:8888/modeloutput/selectOutputByModelId?mid=" + mid);
            ModelOutput[] modelOutput =  mapper.readValue(s1, ModelOutput[].class);//输出表对象

            for (ModelOutput output : modelOutput) {
                //赋值：元数据编号
                deviceOutput.setMetaNum(output.getNumber());
                //赋值；数据编码
                deviceOutput.setCode(output.getOutputCode());
                deviceOutputMapper.save(deviceOutput);
                System.out.println(deviceOutput);
            }
            /**
             * 在参数表中插入：设备编号、参数编号、参数编码、参数值
             */
            DeviceParam deviceParam = new DeviceParam();
            deviceParam.setDeviceNum(number);//设备编号
            //用型号编号查询获取 参数表对象
            String s2 = httpAPIService.doGet("http://192.168.0.25:8888/modelparam/selectParamByModelId?mid=" + mid);
            ModelParam[] modelParam = mapper.readValue(s2, ModelParam[].class);//参数表对象

            for (ModelParam param : modelParam) {
                deviceParam.setParamNum(param.getNumber());//赋值参数码
                deviceParam.setCode(param.getCode());//赋值参数码
                deviceParam.setValue(param.getMDefault());//赋值参数值
                deviceParamMapper.save(deviceParam);
            }
        } catch (Exception exception) {
            log.error("添加设备" ,exception);
            throw exception;
        }

        int result = deviceService.save(device);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }

    }
    //一台设备对一个规则
    @GetMapping("/numberJoinOutPutJoinParamList/{number}")
    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
    @ApiOperation(value = "根据设备编号查询关联的参数表与输出表", notes = "根据设备编号查询关联的参数表与输出表")
    public List<Device> numberJoinOutPutJoinParamList(@PathVariable String number) {
        try {
            List<Device> list = deviceMapper.numberJoinOutPutJoinParamList(number);
            return list;
        } catch (Exception exception) {
            log.error("根据设备编号查询关联的参数表与输出表", exception);
            throw exception;
        }
    }
}
