package com.jc.local.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "设备组信息表的实体属性")
public class Groups {
    @ExcelIgnore
    @ApiModelProperty("记录ID")
    private Integer id;
    @ExcelProperty(value = "编号", index = 0)
    @ApiModelProperty("编号")
    private String number;
    @ExcelProperty(value = "名称",index = 1)
    @ApiModelProperty("名称")
    private String name;
    @ExcelProperty(value = "描述",index = 2)
    @ApiModelProperty("描述")
    private String description;
    @ExcelProperty(value = "删除标志",index = 3)
    @ApiModelProperty("删除标志")
    private String isDel;
    @ExcelProperty(value = "操作标志",index =4)
    @ApiModelProperty("操作标志")
    private String opFlag;
    @ExcelProperty(value = "创建人",index = 5)
    @ApiModelProperty("创建人")
    private String createdBy;
    @ExcelProperty(value = "创建时间", index = 6)
    @ApiModelProperty("创建时间")
    @DateTimeFormat(value = "yyyy/MM/dd")
    @ColumnWidth(value = 20)
    private Date createdTime;
    @ExcelProperty(value = "更新人",index = 7)
    @ApiModelProperty("更新人")
    private String updatedBy;
    @ExcelProperty(value = "更新时间",index = 8)
    @ApiModelProperty("更新时间")
    @ColumnWidth(value = 20)
    @DateTimeFormat(value = "yyyy/MM/dd")
    private Date updatedTime;

}