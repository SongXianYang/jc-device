package com.jc.local.entity.devRepo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ModelOutput {
    private String id;
    /**
     * 设备型号编号;设备型号编号
     */
    private String dmNum;
    /**
     * 元数据编号;数据编号，系统统一生成
     */
    private String number;
    /**
     * 父编号;数据父记录编号，可形成树形结构
     */
    private String parentNum;
    /**
     * 数据编码;输出数据编码
     */
    private String outputCode;
    /**
     * 数据描述;输出数据描述
     */
    private String outputDesc;
    /**
     * 数据类型;输出数据类型，String，Int等
     */
    private String outputType;
    /**
     * 数据公式;输出数据计算公式
     */
    private String outputFormula;
    /**
     * 公式描述;计算公式描述说明
     */
    private String formulaDesc;
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
