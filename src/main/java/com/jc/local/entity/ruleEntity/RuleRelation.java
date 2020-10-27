package com.jc.local.entity.ruleEntity;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//测试树
@Data
@ApiModel(value = "规则关系表实体属性测试树")
@NoArgsConstructor
public class RuleRelation implements Serializable {
    private String id;
    /**
     * 规则链编号;记录编号，系统统一生成，全表唯一
     */
    private String number;
    /**
     * 父规则编号;当前规则父规则编号
     */
    private String pruleNum;
    /**
     * 规则编号;规则编号，关联规则表
     */
    private String ruleNum;
    /**
     * 附加信息;规则关系附加信息
     */
    private String additional;
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
    private Date createdTime;
    /**
     * 更新人;记录更新人
     */
    private String updatedBy;
    /**
     * 更新时间;记录更新时间
     */
    private Date updatedTime;

    private List<RuleRelation> menu;
}
