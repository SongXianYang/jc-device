package com.jc.local.service.impl;

import com.jc.local.entity.ruleEntity.RuleRelation;
import com.jc.local.mapper.RuleRelationMapper;
import com.jc.local.service.RuleRelationService;
import org.apache.commons.lang.StringUtils;
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
        //查询规则关系表 父编号
        List<RuleRelation> ruleRelationList = ruleRelationMapper.selectPruleNum(0);
        //   其余编号
        List<RuleRelation> selectNotPruleNum = ruleRelationMapper.selectNotPruleNum();
        //循环遍历其余编号看看是否有2级或者3级菜单
        for (RuleRelation ruleRelations : selectNotPruleNum) {
            List<RuleRelation> relations = iterateRuleRelation(selectNotPruleNum, ruleRelations.getRuleNum());
            ruleRelations.setMenu(relations);
        }
        //循环遍历父编号
        for (RuleRelation ruleRelation : ruleRelationList) {
            List<RuleRelation> relations = iterateRuleRelation(selectNotPruleNum, ruleRelation.getRuleNum());
            ruleRelation.setMenu(relations);
        }
        return ruleRelationList;
    }

    //循环遍历的编号父传到 iterateRuleRelation（）这个方法
    public List<RuleRelation> iterateRuleRelation(List<RuleRelation> ruleRelationVoList, String pNum) {

        ArrayList<RuleRelation> result = new ArrayList<>();
        for (RuleRelation ruleRelation : ruleRelationVoList) {
            //获取编号
            String ruleNum = ruleRelation.getRuleNum();
            //获取父编号
            String pruleNum = ruleRelation.getPruleNum();
            //判断某pruleNum是否不为空且长度不为0且不由空白符(whitespace)构成“ ”
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
