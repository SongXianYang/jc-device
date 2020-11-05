package com.jc.local.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jc.local.entity.Groups;
import com.jc.local.mapper.GroupsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: SongXianYang
 * @create: 2020-11-04 18:18
 **/
@Slf4j
public class ExcelListener extends AnalysisEventListener {

    @Autowired
    GroupsMapper groupsMapper;
    //可以通过实例获取该值
    private List<Object> dataList = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        dataList.add(object);
        handleBusinessLogic(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //非必要语句，查看导入的数据
        System.out.println("导入的数据 " + dataList.toString());
        //解析结束销毁不用的资源
//        dataList.clear();
    }

    //根据业务自行实现该方法，例如将解析好的dataList存储到数据库中
    private void handleBusinessLogic(Object object) {
        log.info("处理数据！");
//        groupsMapper.save((Groups) object);
        System.out.println(object);
    }

    public List<Object> getDataList() {

        return dataList;
    }

    public void setDataList(List<Object> dataList) {


                this.dataList = dataList;
    }
}
