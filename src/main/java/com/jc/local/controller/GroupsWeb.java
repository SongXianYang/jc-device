package com.jc.local.controller;

import com.jc.local.dto.GroupAndDeviceDTO;
import com.jc.local.entity.Groups;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.service.GroupService;
import com.jc.local.utils.NumberUtils;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//设备组
@Api(tags = "组")
@Slf4j
@RestController
@RequestMapping("Groups")
public class GroupsWeb {

    GroupsMapper groupsMapper;

    GroupService groupService;

    public GroupsWeb(GroupsMapper groupsMapper, GroupService groupService) {
        this.groupsMapper = groupsMapper;
        this.groupService = groupService;
    }

    /**
     * 查询所有组
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有组", notes = "查询所有组")
    public Response<List<Groups>> list() {
        try {
            List<Groups> list = groupsMapper.selectAll();
            return Response.success(list);
        } catch (Exception exception) {
            log.error("查询所有组", exception);
            throw exception;
        }
    }

    /**
     * 根据id删除组
     * @param id
     * @return
     */
    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "组id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除组", notes = "根据id删除组")
    public String deleteId(@PathVariable int id) {
        try {
            int result = groupsMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "您输入的组id可能已经被其他管理员删除，请换种姿势输入哦！";
            }
        } catch (Exception exception) {
            log.error("根据id删除组", exception);
            throw exception;
        }
    }

    /**
     * 添加组
     * @param groups
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "添加组", notes = "添加组")
    public String save(Groups groups) {
        try {
            groups.setNumber(NumberUtils.createNumberKey());
            int result = groupService.save(groups);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加组",exception);
            throw exception;
        }
    }

    /**
     * 更新组
     * @param groups
     * @return
     */
    @PutMapping("update")
    @ApiOperation(value = "更新组", notes = "更新组")
    public String update(Groups groups) {
        try {
            int result = groupsMapper.update(groups);
            if (result >= 1) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } catch (Exception exception) {
            log.error("更新组", exception);
            throw exception;
        }
    }

    /**
     * 根据id查询组
     * @param id
     * @return
     */
    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询组", notes = "根据id查询组")
    @ApiImplicitParam(name = "id", value = "组id", dataType = "int")
    public Response<Groups> getById(@PathVariable Integer id) {
        try {
            if (groupService.selectId(id).getId().equals(id)) {
                Groups byId = groupsMapper.getById(id);
                return Response.success(byId);
            } else {
                return Response.failure("");
            }

        } catch (Exception exception) {
            log.error("根据id查询组", exception);
            throw exception;
        }
    }

    /**
     * 根据组名称查询设备
     * @param groupName
     * @return
     */
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
