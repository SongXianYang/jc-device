package com.jc.local.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "传感器设备输出数据表的实体属性")
public class DeviceOutput {
    @ApiModelProperty("记录ID")
    private Integer id;
    @ApiModelProperty("设备编号")
    private String deviceNum;
    @ApiModelProperty("元数据编号")
    private String metaNum;
    @ApiModelProperty("数据编码")
    private String code;
    @ApiModelProperty("数据值")
    private String value;
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
}