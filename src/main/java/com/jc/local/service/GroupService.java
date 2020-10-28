package com.jc.local.service;

import com.jc.local.entity.Groups;

public interface GroupService {
    //添加设备组
    int save(Groups groups);

    //根据id查id
    Groups selectId(Integer id);
}
