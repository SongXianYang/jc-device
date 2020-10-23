package com.jc.local.dto;

import com.xzixi.swagger2.plus.annotation.IgnoreSwagger2Parameter;
import lombok.Data;

@Data
public class GroupAndDeviceDTO {
    @IgnoreSwagger2Parameter
    private String dName;
    @IgnoreSwagger2Parameter
    private String gName;
}
