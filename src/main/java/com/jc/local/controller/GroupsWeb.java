package com.jc.local.controller;

import com.jc.local.dto.GroupAndDeviceDTO;
import com.jc.local.entity.Groups;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.utils.NumberUtils;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//设备组
@Api(tags = "组接口")
@Slf4j
@RestController
@RequestMapping("Groups")
public class GroupsWeb {

    GroupsMapper groupsMapper;

    public GroupsWeb(GroupsMapper groupsMapper) {
        this.groupsMapper = groupsMapper;
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备组", notes = "查询所有设备组")
    public Response<List<Groups>> list() {
        try {
            List<Groups> list = groupsMapper.selectAll();
            return Response.success(list);
        } catch (Exception exception) {
            log.error("查询所有设备组", exception);
            throw exception;
        }
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备组id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备组", notes = "根据id删除设备组")
    public String deleteId(@PathVariable int id) {
        try {
            int result = groupsMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "删除失败！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备组", exception);
            throw exception;
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备组", notes = "添加设备组")
    public String save(Groups groups) {
        try {
            groups.setNumber(NumberUtils.createNumberKey());
            int result = groupsMapper.save(groups);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加设备组");
            throw exception;
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备组", notes = "更新设备组")
    public String update(Groups groups) {
        try {
            int result = groupsMapper.update(groups);
            if (result >= 1) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } catch (Exception exception) {
            log.error("更新设备组", exception);
            throw exception;
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备组", notes = "根据id查询设备组")
    @ApiImplicitParam(name = "id", value = "设备组id", dataType = "int")
    public Response<Groups> getById(@PathVariable Integer id) {
        try {
            Groups byId = groupsMapper.getById(id);
            return Response.success(byId);
        } catch (Exception exception) {
            log.error("根据id查询设备组", exception);
            throw exception;
        }
    }

    //    根据组名称查询设备
    @GetMapping("selectGroup/{groupName}")
    @ApiOperation(value = "根据组名称查询设备", notes = "根据组组名称查询设备")
    @ApiImplicitParam(name = "groupName", value = "组名称", dataType = "String")
    public Response<List<GroupAndDeviceDTO>> selectGroup(@PathVariable String groupName) {
        try {
            List<GroupAndDeviceDTO> list = groupsMapper.selectDeviceGroup(groupName);
            return Response.success(list);
        } catch (Exception exception) {
            log.error("根据组名称查询设备", exception);
            throw exception;
        }
    }
}
