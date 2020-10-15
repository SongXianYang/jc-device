package com.jc.local.entity;

import lombok.Data;

import java.util.Date;
@Data
public class DeviceTag {
    private Integer id;

    private String deviceNum;

    private String tagContent;

    private String isDel;

    private String opFlag;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

}