package com.jc.local.controller;

import com.jc.local.entity.Groups;
import com.jc.local.mapper.GroupsMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//设备组
@RestController
@RequestMapping("Groups")
public class GroupsWeb {
    @Autowired
    GroupsMapper groupsMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有设备组", notes = "查询所有设备组")
    public List<Groups> list() {
        List<Groups> list = groupsMapper.selectAll();
        return list;
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备组id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备组", notes = "根据id删除设备组")
    public String deleteId(@PathVariable int id) {
        int result = groupsMapper.deleteId(id);
        if (result >= 1) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    @PostMapping("save")
    @ApiOperation(value = "添加设备组", notes = "添加设备组")
    public String save(Groups groups) {
        int result = groupsMapper.save(groups);
        if (result >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备组", notes = "更新设备组")
    public String update(Groups groups) {
        int result = groupsMapper.update(groups);
        if (result >= 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备组", notes = "根据id查询设备组")
    @ApiImplicitParam(name = "id", value = "设备组id", dataType = "int")
    public Groups getById(@PathVariable Integer id) {
        Groups byId = groupsMapper.getById(id);
        return byId;
    }

}
