package com.jc.local.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "设备打标签信息表的实体属性")
public class DeviceTag {
    @ApiModelProperty("记录ID")
    private Integer id;
    @ApiModelProperty("设备编号")
    private String deviceNum;
    @ApiModelProperty("标签内容")
    private String tagContent;
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