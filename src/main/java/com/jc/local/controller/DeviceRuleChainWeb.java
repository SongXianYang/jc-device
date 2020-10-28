package com.jc.local.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceRuleChain;
import com.jc.local.entity.ruleEntity.Chain;
import com.jc.local.http.HttpAPIService;
import com.jc.local.http.HttpResult;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.DeviceRuleChainMapper;
import com.jc.local.service.DeviceRuleChainService;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

//设备规则链链
@Api(tags = "设备规则链")
@Slf4j
@RestController
@RequestMapping("DeviceRuleChain")
public class DeviceRuleChainWeb {

    DeviceRuleChainMapper deviceRuleChainMapper;

    HttpAPIService httpAPIService;

    DeviceMapper deviceMapper;

    DeviceRuleChainService deviceRuleChainService;

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //修改日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public DeviceRuleChainWeb(DeviceRuleChainMapper deviceRuleChainMapper, HttpAPIService httpAPIService,
                              DeviceMapper deviceMapper, DeviceRuleChainService deviceRuleChainService) {
        this.deviceRuleChainMapper = deviceRuleChainMapper;
        this.httpAPIService = httpAPIService;
        this.deviceMapper = deviceMapper;
        this.deviceRuleChainService = deviceRuleChainService;
    }

    /**
     * 查询所有设备规则链
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备规则链", notes = "查询所有设备规则链")
    public Response<List<DeviceRuleChain>> list() {
        try {
            List<DeviceRuleChain> list = deviceRuleChainMapper.selectAll();
            return Response.success(list);
        } catch (Exception exception) {
            log.error("查询所有设备规则链", exception);
            throw exception;
        }
    }

    /**
     * 根据id删除设备规则链
     * @param id
     * @return
     */
    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备规则链id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备规则链", notes = "根据id删除设备规则链")
    public String deleteId(@PathVariable int id) {
        try {
            int result = deviceRuleChainMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "删除失败！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备规则链", exception);
            throw exception;
        }

    }

    /**
     * 添加设备规则链
     * @param deviceRuleChain
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "添加设备规则链", notes = "添加设备规则链")
    public String save(DeviceRuleChain deviceRuleChain) {
        try {
            int result = deviceRuleChainMapper.save(deviceRuleChain);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加设备规则链", exception);
            throw exception;
        }
    }

    /**
     * 更新设备规则链
     * @param deviceRuleChain
     * @return
     */
    @PutMapping("update")
    @ApiOperation(value = "更新设备规则链", notes = "更新设备规则链")
    public String update(DeviceRuleChain deviceRuleChain) {
        try {
            int result = deviceRuleChainMapper.update(deviceRuleChain);
            if (result >= 1) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } catch (Exception exception) {
            log.error("更新设备规则链", exception);
            throw exception;
        }
    }

    /**
     * 根据id查询设备规则链
     * @param id
     * @return
     */
    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备规则链", notes = "根据id查询设备规则链")
    @ApiImplicitParam(name = "id", value = "设备规则链id", dataType = "int")
    public Response<DeviceRuleChain> getById(@PathVariable Integer id) {
        try {
            DeviceRuleChain byId = deviceRuleChainMapper.getById(id);
            if (byId.getId().equals(id)) {
                return Response.success(byId);
            } else {
                return null;
            }
        } catch (Exception exception) {
            log.error("根据id查询设备规则链", exception);
            throw exception;
        }
    }

    /**
     * 使用httpclient添加设备规则链
     * @param cid
     * @param did
     * @return
     * @throws Exception
     */
    //用httpclient获取规则编号
    @PostMapping("chainNumber/{cid}/{did}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "规则链id", dataType = "Integer"),
            @ApiImplicitParam(name = "did", value = "设备id", dataType = "Integer")
    })
    @ApiOperation(value = "使用httpclient添加设备规则链", notes = "使用httpclient添加设备规则链")
    public String chainNumber(@PathVariable Integer cid, @PathVariable Integer did) throws Exception {
        //获取设备编号
        Device device = deviceMapper.getById(did);

        DeviceRuleChain ruleChain = new DeviceRuleChain();

        try {

            String result = httpAPIService.doGet("http://192.168.0.27:8080/ruleChain/selectRuleChain/" + cid);

            //json转换成对象
            Chain chain = mapper.readValue(result, Chain.class);
            System.out.println(chain);
            ruleChain.setDeviceNum(device.getNumber());
            ruleChain.setChainNum(chain.getNumber());
            System.out.println(chain);
            int i = deviceRuleChainService.save(ruleChain);
            if (i >= 1) {
                return "插入规则链编号与设备编号成功!!";
            } else {
                return "error";
            }

        } catch (Exception exception) {
            log.error("使用httpclient添加设备规则链", exception);
            throw exception;
        }

    }
}
