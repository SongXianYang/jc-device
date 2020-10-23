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
    @Autowired
    RuleRelationMapper ruleRelationMapper;
    @Override
    public List<RuleRelation> treeList() {
        List<RuleRelation> ruleRelationList = ruleRelationMapper.selectPruleNum(0);
        List<RuleRelation> selectNotPruleNum = ruleRelationMapper.selectNotPruleNum();
        for (RuleRelation ruleRelation : ruleRelationList) {
            List<RuleRelation> relations = iterateRuleRelation(selectNotPruleNum, ruleRelation.getRuleNum());
            ruleRelation.setMenu(relations);
        }
        return ruleRelationList;
    }

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
