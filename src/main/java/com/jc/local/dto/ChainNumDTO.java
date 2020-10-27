package com.jc.local.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: SongXianYang
 * @create: 2020-10-27 14:53
 **/
@Data
@ApiModel(value = "通过设备编号来查询规则编号")
//通过设备名称来查询规则编号
public class ChainNumDTO {

    private String chainNumber;
}
