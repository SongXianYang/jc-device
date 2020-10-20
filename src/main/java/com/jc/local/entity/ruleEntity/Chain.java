package com.jc.local.entity.ruleEntity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Chain implements Serializable {

    /**
     * 记录ID;记录主键，数据库自增
     */
    private String id;
    /**
     * 编号;记录编号，系统统一生成，全表唯一
     */
    private String number;
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
     * 创建时间;记录创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdTime;
    /**
     * 更新人;记录更新人
     */
    private String updatedBy;
    /**
     * 更新时间;记录更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedTime;
}
