package com.jc.local.entity;

import lombok.Data;

import java.util.Date;

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

}