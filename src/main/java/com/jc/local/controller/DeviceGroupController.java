package com.jc.local.controller;

import com.jc.local.entity.Device;
import com.jc.local.entity.DeviceGroup;
import com.jc.local.entity.Groups;
import com.jc.local.mapper.DeviceGroupMapper;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.dto.GroupAndDeviceDTO;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//设备分组
@Api(tags = "设备分组")
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

    /**
     * 查询所有设备分组
     * @return
     */

    @GetMapping(value = "list",produces="application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有设备分组", notes = "查询所有设备分组")
    public Response<List<DeviceGroup>> selectAll() {
        try {
            List<DeviceGroup> list = deviceGroupMapper.selectAll();
            return Response.success(list);
        } catch (Exception exception) {
            log.error("查询所有设备分组", exception);
            throw exception;
        }
    }

    /**
     * 根据id删除设备分组
     * @param id
     * @return
     */
    @DeleteMapping("deleteId/{id}")
    @ApiImplicitParam(name = "id", value = "设备组id", required = true, dataType = "int")
    @ApiOperation(value = "根据id删除设备分组", notes = "根据id删除设备分组")
    public String deleteId(@PathVariable int id) {
        try {
            int result = deviceGroupMapper.deleteId(id);
            if (result >= 1) {
                return "删除成功！";
            } else {
                return "您输入的设备id可能已经被其他管理员删除，请换种姿势输入哦！";
            }
        } catch (Exception exception) {
            log.error("根据id删除设备分组", exception);
            throw exception;
        }
    }

    /**
     * 添加设备分组
     * @param did
     * @param gid
     * @return
     */
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
            deviceGroup.setIsDel("0");
            deviceGroup.setOpFlag("A");
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

    /**
     * 更新设备分组
     * @param deviceGroup
     * @return
     */
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

    /**
     * 根据id查询设备分组
     * @param id
     * @return
     */
    @GetMapping(value = "byId/{id}",produces="application/json;charset=UTF-8")
    @ApiOperation(value = "根据id查询设备分组", notes = "根据id查询设备分组")
    @ApiImplicitParam(name = "id", value = "设备分组id", dataType = "int")
    public Response<DeviceGroup> getById(@PathVariable Integer id) {
        try {
            DeviceGroup byId =deviceGroupMapper.getById(id);
            if (byId.getId().equals(id)) {
                return Response.success(byId);
            }
            return null;
        } catch (Exception exception) {
            log.error("根据id查询设备分组", exception);
            throw exception;
        }
    }

}
