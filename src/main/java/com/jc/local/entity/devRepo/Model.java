package com.jc.local.entity.devRepo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "传感器设备型号信息表实体属性")
public class Model implements Serializable {
    private Integer id;
    /**
     * 设备型号编码;设备型号编码，系统统一形成
     */
    private String number;
    /**
     * 设备名称;设备名称
     */
    private String name;
    /**
     * 设备型号;设备具体型号标识
     */
    private String devModel;
    /**
     * 设备版本;设备型号版本
     */
    private String version;
    /**
     * 厂家编码;设备厂家编码
     */
    private String manuNum;
    /**
     * 设备类型;设备类型编码
     */
    private String typeCode;
    /**
     * 手册URL;设备手册文档URL
     */
    private String docUrl;
    /**
     * 删除标志;删除标志，0-未删，1-已删
     */
    private String isDel;
    /**
     * 操作标志;操作标志，A-增，D-删，U-改
     */
    private String opFlag;
    /**
     * 创建人;记录创建人
     */
    private String createdBy;
    /**
     * 创建时间;创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdTime;
    /**
     * 更新人;记录更新人
     */
    private String updatedBy;
    /**
     * 更新时间;更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedTime;

}
