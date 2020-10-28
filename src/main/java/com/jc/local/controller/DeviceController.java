package com.jc.local.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.pagehelper.PageInfo;
import com.jc.local.dto.ChainNumDTO;
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
@Api(tags = "设备")
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

    /**
     * objectMapper,将Java对象转化为json字符串、json字符串转换成Java对象、
     * 对象<=>数组
     * List<=>json字符串
     * Map<=>json字符串
     * 在这里主要用的是：json字符串转换成Java对象
     */

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
     * 查询所有设备
     * Response  简单的一个响应类
     * 每一个controller接口都用try{}catch{}进行异常捕获
     *
     * @return list
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有设备", notes = "查询所有设备")
    public Response<List<Device>> selectAll() {
        try {
            List<Device> list = deviceService.selectAll();
            return Response.success(list);
        } catch (Exception e) {
            log.error("查询所有设备", e);
            throw e;
        }
    }

    /**
     * 根据id删除设备
     *
     * @param id
     * @return
     * @PathVariable 这个注解  在访问接口的时候通过斜杠（/）传参
     */
    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备", notes = "根据id删除设备")
    public String deleteId(@PathVariable int id) {
        try {
            int result = deviceMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "您输入的设备id可能已经被其他管理员删除，请换种姿势输入哦！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备", exception);
            throw exception;
        }
    }

    /**
     * 更新设备
     *
     * @param device
     * @return
     */
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

    /**
     * 根据id查询设备
     *
     * @param id
     * @return
     */
    @GetMapping(value = "byId/{id}", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "根据id查询设备", notes = "根据id查询设备")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public Response<Device> getById(@PathVariable Integer id) {
        try {
            Device byId = deviceMapper.getById(id);
            if (byId.getId().equals(id)) {
                return Response.success(byId);
            } else {
                return null;
            }

        } catch (Exception exception) {
            log.error("根据id查询设备", exception);
            return Response.failure("根据id查询设备失败");
        }
    }

    /**
     * 根据ids进行批量删除
     *
     * @param pdId
     * @return
     */
    @DeleteMapping("deleteIds/{ids}")
    @ApiOperation(value = "根据ids进行批量删除", notes = "根据ids进行批量删除")
    @ApiImplicitParam(name = "ids", value = "设备ids", dataType = "List<Integer>")
    public String deleteIds(@PathVariable("ids") String pdId) {
        try {
            //将参数pdId使用正则split（）去分割字符串，以逗号隔开
            String[] pdIds = pdId.split(",");
            //创建建一个list集合
            ArrayList<Integer> ids = new ArrayList<>();
            //循环遍历数组中的数据
            for (String str : pdIds) {
                // parseInt 方法得到的原始数据类型的一个特定的字符串 ，十进制字符串形式
                //然后放进List集合中
                ids.add(Integer.parseInt(str));
            }
            int result = deviceMapper.deleteIds(ids);
            if (result >= 1) {
                return "批量删除成功！！";
            } else {
                return "批量删除失败";
            }
        } catch (Exception e) {
            log.error("根据ids进行批量删除", e);
            throw e;
        }
    }

    /**
     * 设备启动
     *
     * @param id
     * @return
     */

    @PutMapping("start/{id}")
    @ApiOperation(value = "设备启动", notes = "设备启动")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public String start(@PathVariable("id") int id) {
        try {
            //update 更新设备状态
            deviceService.state(id, "1");
            return "启动成功";
        } catch (Exception exception) {
            log.error("设备启动", exception);
            throw exception;
        }
    }

    /**
     * 设备停止
     *
     * @param id
     * @return
     */
    @PutMapping("stop/{id}")
    @ApiOperation(value = "设备停止", notes = "设备停止")
    @ApiImplicitParam(name = "id", value = "停止设备id", dataType = "int")
    public String stop(@PathVariable int id) {
        try {
            deviceService.state(id, "0");
            return "停止成功";
        } catch (Exception exception) {
            log.error("设备停止", exception);
            throw exception;
        }
    }


    /**
     * 设备逻辑删除
     *
     * @param id
     * @return
     */
    @PutMapping("idDelete/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "Integer")
    @ApiOperation(value = "设备逻辑删除", notes = "设备逻辑删除")
    public String idDelete(@PathVariable Integer id) {
        try {
            //sql语句直接写死（update）
            int i = deviceMapper.idDelete(id);
            if (i >= 1) {
                return "逻辑删除成功";
            } else {
                return "逻辑删除失败";
            }
        } catch (Exception exception) {
            log.error("设备逻辑删除", exception);
            throw exception;
        }
    }

    /**
     * 添加设备（同时插入设备参数与输出）
     * 说明：在插入一台设备时，从设备库中同步两个表的数据（参数表、输出表）
     *
     * @param mid
     * @return
     * @throws Exception
     */
    @PostMapping("insertAll/{mid}")
    @ApiImplicitParam(name = "mid", value = "设备型号id", dataType = "int")
    @ApiOperation(value = "添加设备（同时插入设备参数与输出）", notes = "添加设备（同时插入设备参数与输出）")
    public String insertAll(@PathVariable Integer mid) throws Exception {
        //先获取设备对象
        Device device = new Device();
        //一个随机数的工具类
        String number = NumberUtils.createNumberKey();
        try {
            //设置编号，由工具类生成。
            device.setNumber(number);
            //使用httpAPIService对象去获取doGet方法，拿到IP地址为:192.168.0.25服务传来当前设备型号mid的json字符串
            String s = httpAPIService.doGet("http://192.168.0.25:8888/model/selectOne/" + mid);
            //获取型号编号对象
            /**
             *将json字符串转换成Java对象 （readValue）使用这个方法
             * 属性赋值
             */
            Model models = mapper.readValue(s, Model.class);
            device.setDmNum(models.getNumber());
            device.setName(models.getName());
            device.setDevSn(models.getManuNum() + models.getNumber());
            device.setIsDel("0");

            /**
             * 在输出表中插入：设备编号、元数据编号、数据编码
             */
            DeviceOutput deviceOutput = new DeviceOutput();
            deviceOutput.setDeviceNum(number);
            //利用设备型号id
            String s1 = httpAPIService
                    .doGet("http://192.168.0.25:8888/modeloutput/selectOutputByModelId?mid=" + mid);
            /**
             * 当前服务192.168.0.25:8888，传过来的是一个list集合。
             * 需要用 ModelOutput【】数组对象来接受集合
             * 然后循环遍历数组中的集合
             */
            ModelOutput[] modelOutput = mapper.readValue(s1, ModelOutput[].class);//输出表对象
            for (ModelOutput output : modelOutput) {
                //赋值：元数据编号
                deviceOutput.setMetaNum(output.getNumber());
                //赋值；数据编码
                deviceOutput.setCode(output.getOutputCode());
                deviceOutputMapper.save(deviceOutput);
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
                deviceParam.setValue(param.getMpDefault());//赋值参数值
                deviceParamMapper.save(deviceParam);
            }
            int result = deviceService.save(device);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加设备", exception);
            throw exception;
        }

    }

    /**
     * 根据设备编号查询关联的参数表与输出表信息
     *
     * @param number
     * @return
     */
    @GetMapping(value = "/numberJoinOutPutJoinParamList/{number}", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "number", value = "设备编号number", dataType = "String")
    @ApiOperation(value = "根据设备编号查询关联的参数表与输出表", notes = "根据设备编号查询关联的参数表与输出表")
    public Response<List<Device>> numberJoinOutPutJoinParamList(@PathVariable String number) {
        try {
            List<Device> list = deviceMapper.numberJoinOutPutJoinParamList(number);
            return Response.success(list);
        } catch (Exception exception) {
            log.error("根据设备编号查询关联的参数表与输出表", exception);
            throw exception;
        }
    }

    /**
     * 根据设备名称查询对应规则链
     *
     * @param deviceName
     * @return
     */

    @GetMapping(value = "deviceNameJoinChainNumList/{deviceName}", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "根据设备名称查询对应规则链", notes = "根据设备名称查询对应规则链")
    @ApiImplicitParam(name = "deviceName", value = "设备名称", dataType = "String")
    public Response<List<ChainNumDTO>> deviceNameJoinChainNumList(@PathVariable String deviceName) {
        try {
            List<ChainNumDTO> chainNumDTOList = deviceService.deviceNameJoinChainNumList(deviceName);
            return Response.success(chainNumDTOList);
        } catch (Exception e) {
            log.error("根据设备名称查询对应规则链", e);
            throw e;
        }
    }

    /**
     * 根据设备编号删除设备及该设备输出表数据和参数表数据
     *
     * @param deviceNumber
     * @return
     */
    @DeleteMapping("deleteDeviceNumberJoinOutputJoinParam/{deviceNumber}")
    @ApiOperation(value = "根据设备编号删除设备及该设备输出表数据和参数表数据",
            notes = "根据设备编号删除设备及该设备输出表数据和参数表数据")
    @ApiImplicitParam(name = "deviceNumber", value = "设备编号", dataType = "String")
    public String deleteDeviceNumberJoinOutputJoinParam(@PathVariable String deviceNumber) {
        try {
            int count = deviceService.deleteDeviceNumberJoinOutputJoinParam(deviceNumber);
            if (count >= 1) {
                return "根据设备编号删除设备及该设备输出表数据和参数表数据,删除成功";
            } else {
                return "您输入的”设备编号“无法找到哦！！,删除失败";
            }
        } catch (Exception e) {
            log.error("根据设备编号删除设备及该设备输出表数据和参数表数据", e);
            throw e;
        }
    }

    /**
     * 分页查询设备每页显示2条数据
     * @param pageNum
     * @return
     */
    @GetMapping(value = "pageFindAll/{pageNum}", produces = "application/json;charset=UTF-8")
    @ApiOperation(value ="分页查询设备每页显示2条数据",notes = "分页查询设备每页显示2条数据")
    @ApiImplicitParam(name = "pageNum",value = "第几页开始",dataType = "int")
    public PageInfo<Device> pageFindAll(@PathVariable int pageNum) {
        try {
            PageInfo<Device> pageInfo = deviceService.pageFindAll(pageNum, 2);
            return pageInfo;
        } catch (Exception e) {
            log.error("分页查询设备每页显示2条数据", e);
            throw e;
        }
    }
}
