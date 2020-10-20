package com.jc.local.entity.devRepo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class ModelParam implements Serializable {

    private Integer id;
    /**
     * 设备型号编号;设备型号编码，关联设备型号表
     */
    private String dmNum;
    /**
     * 参数编号;参数编号，系统统一生成
     */
    private String number;
    /**
     * 父参数编号;父参数编号，可用于形成树结构
     */
    private String parentNum;
    /**
     * 参数类型;参数类型，0-连接，1-控制
     */
    private String type;
    /**
     * 参数名;参数名称
     */
    private String name;
    /**
     * 参数码;参数码
     */
    private String code;
    /**
     * 默认值;参数默认值，多选用英文","分割
     */
    @JsonProperty("mDefault")
    private String mDefault;
    /**
     * 参数描述;参数描述
     */
    private String description;
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
