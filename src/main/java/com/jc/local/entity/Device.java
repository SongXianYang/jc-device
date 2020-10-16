package com.jc.local.entity;

import lombok.Data;



import java.util.Date;
import java.util.List;

@Data
public class Device {
    private Integer id;

    private String number;

    private String dmNum;

    private String name;

    private String description;

    private String status;

    private String position;

    private String mpNum;

    private String devSn;

    private String isDel;

    private String opFlag;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;
    //    一台设备对一个设备输出
    private DeviceOutput deviceOutput;
    //    一台设备对多个参数表
    private List<DeviceParam> deviceParamsList;
    //一台设备对对一个规则
    private DeviceRuleChain deviceRuleChain;
}