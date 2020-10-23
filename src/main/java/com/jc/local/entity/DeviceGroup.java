package com.jc.local.entity;

import com.xzixi.swagger2.plus.annotation.EnableSwagger2Plus;
import com.xzixi.swagger2.plus.annotation.IgnoreSwagger2Parameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "设备分组情况表实体属性")
public class DeviceGroup {
    @ApiModelProperty("记录ID")
    private Integer id;
    @ApiModelProperty("设备编号")
    private String deviceNum;
    @ApiModelProperty("分组编号")
    private String groupNum;
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