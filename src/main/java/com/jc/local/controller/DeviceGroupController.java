package com.jc.local.controller;

import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceGroup;
import com.jc.local.entity.Groups;
import com.jc.local.mapper.DeviceGroupMapper;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.dto.GroupAndDeviceDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//设备分组
@Api(tags = "设备分组接口")
@RestController
@RequestMapping("DeviceGroup")
@Slf4j
public class DeviceGroupController {

    DeviceGroupMapper deviceGroupMapper;

    DeviceMapper deviceMapper;

    GroupsMapper groupsMapper;

    public DeviceGroupController(DeviceGroupMapper deviceGroupMapper, DeviceMapper deviceMapper, GroupsMapper groupsMapper) {
        this.deviceGroupMapper = deviceGroupMapper;
        this.deviceMapper = deviceMapper;
        this.groupsMapper = groupsMapper;
    }

    @GetMapping("list")
    @ApiOperation(value = "查询所有设备分组", notes = "查询所有设备分组")
    public List<DeviceGroup> selectAll() {
        try {
            List<DeviceGroup> list = deviceGroupMapper.selectAll();
            return list;
        } catch (Exception exception) {
            log.error("查询所有设备分组", exception);
            throw exception;
        }
    }

    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备分组", notes = "根据id删除设备分组")
    public String deleteId(@PathVariable int id) {
        try {
            int result = deviceGroupMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "删除失败！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备分组", exception);
            throw exception;
        }
    }

    @PostMapping("save/{did}/{gid}")
    @ApiOperation(value = "添加设备分组", notes = "添加设备分组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "did", value = "设备id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "gid", value = "组id", required = true, dataType = "int")
    })
    public String save(@PathVariable Integer did, @PathVariable Integer gid) {
        try {
            //通过getById（id）方法获取到设备与组  的  对象
            Device device = deviceMapper.getById(did);
            Groups groups = groupsMapper.getById(gid);
            //获取对象设备组对象
            DeviceGroup deviceGroup = new DeviceGroup();
            //组编号与设备编号赋值
            deviceGroup.setDeviceNum(device.getNumber());
            deviceGroup.setGroupNum(groups.getNumber());
            int result = deviceGroupMapper.save(deviceGroup);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加设备分组", exception);
            throw exception;
        }
    }

    @PutMapping("update")
    @ApiOperation(value = "更新设备分组", notes = "更新设备分组")
    public String update(DeviceGroup deviceGroup) {
        try {
            int result = deviceGroupMapper.update(deviceGroup);
            if (result >= 1) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } catch (Exception exception) {
            log.error("更新设备分组", exception);
            throw exception;
        }
    }

    @GetMapping("byId/{id}")
    @ApiOperation(value = "根据id查询设备分组", notes = "根据id查询设备分组")
    @ApiImplicitParam(name = "id", value = "设备id", dataType = "int")
    public DeviceGroup getById(@PathVariable Integer id) {
        try {
            DeviceGroup byId = deviceGroupMapper.getById(id);
            return byId;
        } catch (Exception exception) {
            log.error("根据id查询设备分组", exception);
            throw exception;
        }
    }

    //    根据组编号查询设备
    @GetMapping("selectGroup/{groupNum}")
    @ApiOperation(value = "根据组编号查询设备", notes = "根据组编号查询设备")
    @ApiImplicitParam(name = "groupNum", value = "组编号", dataType = "String")
    public List<GroupAndDeviceDTO> selectGroup(@PathVariable String groupNum) {
        try {
            List<GroupAndDeviceDTO> list = deviceGroupMapper.selectDeviceGroup(groupNum);
            return list;
        } catch (Exception exception) {
            log.error("根据组编号查询设备", exception);
            throw exception;
        }
    }
}
