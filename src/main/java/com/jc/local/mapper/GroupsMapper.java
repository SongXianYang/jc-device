package com.jc.local.mapper;


import com.jc.local.entity.Groups;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

//设备组
@Mapper
public interface GroupsMapper {
    //查询所有设备组
    List<Groups> selectAll();

    //根据id查询单个设备组
    Groups getById(Integer id);

    //添加设备组
    int save(Groups groups);

    //删除设备组
    int deleteId(Integer id);

    //更新设备组
    int update(Groups groups);


}
