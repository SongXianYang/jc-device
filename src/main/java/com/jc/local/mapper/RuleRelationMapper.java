package com.jc.local.mapper;

import com.jc.local.entity.ruleEntity.RuleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface RuleRelationMapper {
    //查询所有菜单
    @Select("SELECT * from rule_relation")
    List<RuleRelation> treeList();

    //根据父id查询所有父菜单
    @Select("select * from rule_relation where PRULE_NUM=#{pruleNum}")
    @Results(
            value = {
                    @Result(id = true, column = "ID", property = "id"),
                    @Result(column = "NUMBER", property = "number"),
                    @Result(column = "PRULE_NUM", property = "pruleNum"),
                    @Result(column = "RULE_NUM", property = "ruleNum"),
                    @Result(column = "ADDITIONAL", property = "additional"),
                    @Result(column = "IS_DEL", property = "isDel"),
                    @Result(column = "OP_FLAG", property = "opFlag"),
                    @Result(column = "CREATED_BY", property = "createdBy"),
                    @Result(column = "CREATED_TIME", property = "createdTime"),
                    @Result(column = "UPDATED_BY", property = "updatedBy"),
                    @Result(column = "UPDATED_TIME", property = "updatedTime")
            }
    )
    List<RuleRelation> selectPruleNum(Integer pruleNum);

    //查询一级菜单以外的菜单
    @Select("select * from rule_relation where PRULE_NUM != 0")
    @Results(
            value = {
                    @Result(id = true, column = "ID", property = "id"),
                    @Result(column = "NUMBER", property = "number"),
                    @Result(column = "PRULE_NUM", property = "pruleNum"),
                    @Result(column = "RULE_NUM", property = "ruleNum"),
                    @Result(column = "ADDITIONAL", property = "additional"),
                    @Result(column = "IS_DEL", property = "isDel"),
                    @Result(column = "OP_FLAG", property = "opFlag"),
                    @Result(column = "CREATED_BY", property = "createdBy"),
                    @Result(column = "CREATED_TIME", property = "createdTime"),
                    @Result(column = "UPDATED_BY", property = "updatedBy"),
                    @Result(column = "UPDATED_TIME", property = "updatedTime")
            }
    )
    List<RuleRelation> selectNotPruleNum();
}
