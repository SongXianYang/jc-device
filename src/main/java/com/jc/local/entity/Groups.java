package com.jc.local.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Groups {
    private Integer id;

    private String number;

    private String name;

    private String description;

    private String isDel;

    private String opFlag;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

}