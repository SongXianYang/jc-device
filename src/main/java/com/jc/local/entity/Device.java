package com.jc.local.entity;

import com.xzixi.swagger2.plus.annotation.IgnoreSwagger2Parameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value = "传感器设备信息表实体属性")
@Data
public class Device {
    @ApiModelProperty("记录ID")
    private Integer id;
    @ApiModelProperty(value = "设备编号", hidden = true)
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
    @ApiModelProperty(value = "参数表")
    @IgnoreSwagger2Parameter // 只需要添加注解就可以在文档中排除参数
    private List<DeviceParam> deviceParamsList;
    //一台设备对对一个规则
    @IgnoreSwagger2Parameter // 只需要添加注解就可以在文档中排除参数
    @ApiModelProperty(value = "规则")
    private DeviceRuleChain deviceRuleChain;
    //    一台设备对多个设备输出表
    @ApiModelProperty(value = "设备输出")
    @IgnoreSwagger2Parameter // 只需要添加注解就可以在文档中排除参数
    private List<DeviceOutput> deviceOutputList;
}