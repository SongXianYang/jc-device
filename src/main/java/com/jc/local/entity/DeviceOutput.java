package com.jc.local.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceOutput {
    private Integer id;

    private String deviceNum;

    private String metaNum;

    private String code;

    private String value;

    private String isDel;

    private String opFlag;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;
}