package com.jc.local.service.impl;

import com.jc.local.entity.ruleEntity.RuleRelation;
import com.jc.local.mapper.RuleRelationMapper;
import com.jc.local.service.RuleRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleRelationServiceImpl implements RuleRelationService {
    RuleRelationMapper ruleRelationMapper;

    public RuleRelationServiceImpl(RuleRelationMapper ruleRelationMapper) {
        this.ruleRelationMapper = ruleRelationMapper;
    }

    @Override
    public List<RuleRelation> treeList() {
        List<RuleRelation> ruleRelationList = ruleRelationMapper.selectPruleNum(0);
        List<RuleRelation> selectNotPruleNum = ruleRelationMapper.selectNotPruleNum();
        for (RuleRelation ruleRelations : selectNotPruleNum) {
            List<RuleRelation> relations = iterateRuleRelation(selectNotPruleNum, ruleRelations.getRuleNum());
            ruleRelations.setMenu(relations);
        }
        for (RuleRelation ruleRelation : ruleRelationList) {
            List<RuleRelation> relations = iterateRuleRelation(selectNotPruleNum, ruleRelation.getRuleNum());
            ruleRelation.setMenu(relations);
        }
        return ruleRelationList;
    }

    //循环遍历其余的菜单，看看是否有其他的二级菜单等一次循环下去。返回二级菜单或者或者三级菜单，返回给处理父菜单的逻辑。进行循环遍历。
    public List<RuleRelation> iterateRuleRelation(List<RuleRelation> ruleRelationVoList, String pNum) {
        ArrayList<RuleRelation> result = new ArrayList<>();
        for (RuleRelation ruleRelation : ruleRelationVoList) {
            //获取编号
            String ruleNum = ruleRelation.getRuleNum();
            //获取父编号
            String pruleNum = ruleRelation.getPruleNum();
            if (StringUtils.isNotBlank(pruleNum)) {
                if (pruleNum.equals(pNum)) {
                    //递归查询当前子菜单的子菜单
                    List<RuleRelation> list = iterateRuleRelation(ruleRelationVoList, ruleNum);
                    ruleRelation.setMenu(list);
                    result.add(ruleRelation);
                }
            }
        }
        return result;
    }
}
