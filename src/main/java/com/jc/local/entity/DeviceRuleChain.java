package com.jc.local.entity;

import lombok.Data;

import java.util.Date;
@Data
public class DeviceRuleChain {
    private Integer id;

    private String deviceNum;
    /** 规则链编号;规则链编号，关联规则链表 */
    private String chainNum ;

    private String isDel;

    private String opFlag;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

}