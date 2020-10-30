package com.jc.local.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "通过组的名称查询设备")
//通过组的名称查询设备
public class GroupAndDeviceDTO {
    private String dName;
}
