package com.jc.local.controller;

import com.jc.local.entity.Device;
import com.jc.local.entity.Groups;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.service.DeviceService;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Slf4j
@RestController
@RequestMapping("/test")
@Api(value = "http测试")
public class TestHttpClient {
    private final HttpAPIService httpAPIService;
    private final DeviceService deviceService;
    private final GroupsMapper groupsMapper;
    @Autowired
    DeviceMapper deviceMapper;
    public TestHttpClient(HttpAPIService httpAPIService, DeviceService deviceService, GroupsMapper groupsMapper) {
        this.httpAPIService = httpAPIService;
        this.deviceService = deviceService;
        this.groupsMapper = groupsMapper;
    }

    @ApiOperation(value = "测试APP请求功能是否正常", tags = "http测试接口")
    @GetMapping(value = "http", produces = "application/json")
    public Response<String> test() throws Exception {
        String apiResult = httpAPIService.doGet("https://www.baidu.com");
        return Response.success(apiResult);
    }

    @GetMapping("groups")
    public String getGroups() throws Exception {
        String s = httpAPIService.doGet("http://localhost:9090/Groups/list");
        System.out.println(s);
        return "获取组数据";
    }

    @GetMapping("GroupById/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "Integer")
    public void GroupById(@PathVariable Integer id) {
        try {
            httpAPIService.doGet("http://localhost:9090/Groups/byId/" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试获取组表数据  然后插入到我的组表
    @PostMapping("groupAdd/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "Integer")
    public String groupAdd(@PathVariable Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        Groups groups = groupsMapper.getById(id);
//        int i=5;
//        groups.setId(i++);
//        Device device = new Device();
//        device.setNumber(groups.getNumber());
//        device.setName(groups.getName());
//        device.setUpdatedBy(groups.getUpdatedBy());

        map.put("number", groups.getNumber());
        map.put("name", groups.getName());
        map.put("updateBy", groups.getUpdatedBy());

        try {
            httpAPIService.doGet("http://localhost:9090/Groups/byId/" + id, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("============================"+map);
        int save = groupsMapper.save(groups);
        if (save >= 1) {
            return "插入成功";
        } else {
            return "插入失败";
        }
    }
}
