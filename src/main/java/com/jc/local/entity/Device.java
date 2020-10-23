package com.jc.local.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@ApiModel(value = "传感器设备信息表实体属性")
@Data
public class Device {
    @ApiModelProperty("记录ID")
    private Integer id;
    @ApiModelProperty("设备编号")
    private String number;
    @ApiModelProperty("型号编号")
    private String dmNum;
    @ApiModelProperty("设备名称")
    private String name;
    @ApiModelProperty("设备描述")
    private String description;
    @ApiModelProperty("设备状态")

    private String status;
    @ApiModelProperty("安装位置")

    private String position;
    @ApiModelProperty("测点编号")

    private String mpNum;
    @ApiModelProperty("设备出厂编号")

    private String devSn;
    @ApiModelProperty("删除标志")

    private String isDel;
    @ApiModelProperty("操作标志")

    private String opFlag;
    @ApiModelProperty("创建人")

    private String createdBy;
    @ApiModelProperty("创建时间")

    private Date createdTime;
    @ApiModelProperty("更新人")

    private String updatedBy;
    @ApiModelProperty("更新时间")
    private Date updatedTime;

    //    一台设备对多个参数表
    @ApiModelProperty("参数表")
    private List<DeviceParam> deviceParamsList;
    //一台设备对对一个规则
    @ApiModelProperty("规则")
    private DeviceRuleChain deviceRuleChain;
    //    一台设备对多个设备输出表
    @ApiModelProperty("设备输出")
    private List<DeviceOutput> deviceOutputList;
}