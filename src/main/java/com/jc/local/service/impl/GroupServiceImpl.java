package com.jc.local.service.impl;

import com.jc.local.entity.Groups;
import com.jc.local.mapper.GroupsMapper;
import com.jc.local.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: SongXianYang
 * @create: 2020-10-27 11:29
 **/
@Service
public class GroupServiceImpl implements GroupService {

    GroupsMapper groupsMapper;

    public GroupServiceImpl(GroupsMapper groupsMapper) {
        this.groupsMapper = groupsMapper;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int save(Groups groups) {
        return groupsMapper.save(groups);
    }
}
