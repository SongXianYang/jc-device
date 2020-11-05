package com.jc.local.controller;

import com.alibaba.excel.ExcelReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jc.local.dto.GroupAndDeviceDTO;
import com.jc.local.entity.Groups;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.service.GroupService;
import com.jc.local.utils.ExcelListener;
import com.jc.local.utils.ExcelUtils;
import com.jc.local.utils.NumberUtils;
import com.jc.local.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.sl.usermodel.Sheet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * 查询所有组
     *
     * @return
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
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
     *
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
     *
     * @param groups
     * @return
     */
    @PostMapping("save")
    @ApiOperation(value = "添加组", notes = "添加组")
    public String save(Groups groups) {
        try {
            groups.setIsDel("0");
            groups.setOpFlag("A");
            groups.setNumber(NumberUtils.createNumberKey());
            int result = groupService.save(groups);
            if (result >= 1) {
                return "添加成功";
            } else {
                return "添加失败";
            }
        } catch (Exception exception) {
            log.error("添加组", exception);
            throw exception;
        }
    }

    /**
     * 更新组
     *
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
     *
     * @param id
     * @return
     */
    @GetMapping(value = "byId/{id}", produces = "application/json;charset=UTF-8")
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
     *
     * @param groupName
     * @return
     */
    @GetMapping(value = "selectGroup/{groupName}", produces = "application/json;charset=UTF-8")
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

    @GetMapping(value = "excelGroupExport", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "设备组导出", notes = "设备组导出")
    /**
    *@Description: 设备组导出
    *@Param: [response]
    *@return: java.lang.String
    *@Author: SongXianYang
    *@date: 2020/11/4
    */
    public String excelGroupExport(HttpServletResponse response) throws Exception {
        List<Groups> groupsList = groupsMapper.selectAll();
        try {
            ExcelUtils.export2Web(response, "设备组表", "设备组", Groups.class, groupsList);
            return "设备组导出成功";
        } catch (Exception e) {
            log.error("设备组导出", e);
            throw e;
        }
    }


    @PostMapping(value = "import", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "设备组文件文件导入",notes = "设备组文件文件导入")
    public void UserImport(@RequestBody MultipartFile excel) throws IOException {
        try {
            List<Groups> groupsList = ExcelUtils.readExcel(excel, Groups.class);
            System.out.println("------------"+groupsList.toString());
            for (Groups group : groupsList) {
                Groups groupEntity = new Groups();
                groupEntity.setName(group.getName());
                groupEntity.setNumber(group.getNumber());
                groupEntity.setDescription(group.getDescription());
                groupEntity.setIsDel(group.getIsDel());
                groupEntity.setOpFlag(group.getOpFlag());
                groupEntity.setCreatedBy(group.getCreatedBy());
                groupEntity.setCreatedTime(group.getCreatedTime());
                groupEntity.setUpdatedBy(group.getUpdatedBy());
                groupEntity.setUpdatedTime(group.getUpdatedTime());
                groupsMapper.save(groupEntity);
            }
        } catch (Exception e) {
            log.error("设备组文件文件导入", e);
            throw e;
        }
    }
}
