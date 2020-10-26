package com.jc.local.controller;

import com.jc.local.entity.Device;
import com.jc.local.entity.Groups;
import com.jc.local.entity.ruleEntity.RuleRelation;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.service.DeviceService;
import com.jc.local.service.RuleRelationService;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/test")
@Api(tags = "http测试")
public class TestHttpClient {

    HttpAPIService httpAPIService;
    DeviceService deviceService;
    GroupsMapper groupsMapper;
    DeviceMapper deviceMapper;
    RuleRelationService ruleRelationService;

    public TestHttpClient(HttpAPIService httpAPIService, DeviceService deviceService, GroupsMapper groupsMapper, DeviceMapper deviceMapper, RuleRelationService ruleRelationService) {
        this.httpAPIService = httpAPIService;
        this.deviceService = deviceService;
        this.groupsMapper = groupsMapper;
        this.deviceMapper = deviceMapper;
        this.ruleRelationService = ruleRelationService;
    }

    @ApiOperation(value = "测试APP请求功能是否正常")
    @GetMapping(value = "http", produces = "application/json")
    public Response<String> test() throws Exception {
        try {
            String apiResult = httpAPIService.doGet("https://www.baidu.com");
            return Response.success(apiResult);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping("groups")
    public String getGroups() throws Exception {
        try {
            String s = httpAPIService.doGet("http://localhost:9090/Groups/list");
            System.out.println(s);
            return "获取组数据";
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping("GroupById/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "Integer")
    public void GroupById(@PathVariable Integer id) throws Exception {
        try {
            httpAPIService.doGet("http://localhost:9090/Groups/byId/" + id);
        } catch (Exception e) {
            throw e;
        }
    }

    //测试获取组表数据  然后插入到我的组表
    @PostMapping("groupAdd/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "Integer")
    public String groupAdd(@PathVariable Integer id) throws Exception {

        HashMap<String, Object> map = new HashMap<>();
        Groups groups = groupsMapper.getById(id);
        map.put("number", groups.getNumber());
        map.put("name", groups.getName());
        map.put("updateBy", groups.getUpdatedBy());

        try {
            httpAPIService.doGet("http://localhost:9090/Groups/byId/" + id, map);
            int save = groupsMapper.save(groups);
            if (save >= 1) {
                return "插入成功";
            } else {
                return "插入失败";
            }
        } catch (Exception e) {
            throw e;
        }

    }
    @GetMapping("treeList")
    public List<RuleRelation> treeList() {
        try {
            List<RuleRelation> list = ruleRelationService.treeList();
            return list;
        } catch (Exception exception) {
            throw exception;
        }
    }
}
