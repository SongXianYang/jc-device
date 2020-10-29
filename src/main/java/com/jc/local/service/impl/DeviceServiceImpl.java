package com.jc.local.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.local.dto.ChainNumDTO;
import com.jc.local.entity.Device;
import com.jc.local.http.HttpAPIService;
import com.jc.local.mapper.DeviceMapper;
import com.jc.local.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {

    HttpAPIService httpAPIService;
    DeviceMapper deviceMapper;

    public DeviceServiceImpl(DeviceMapper deviceMapper, HttpAPIService httpAPIService) {
        this.deviceMapper = deviceMapper;
        this.httpAPIService = httpAPIService;
    }

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //修改日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public List<Device> selectAll() {
        return deviceMapper.selectAll();
    }

    @Override
    public void state(Integer id, String started) {
        Device device = deviceMapper.getById(id);
        device.setStatus(started);
        deviceMapper.startStop(device);
    }

    @Override
    /**
     * rollbackFor 触发回滚的异常，默认是RuntimeException和Error
     * 不加   事务回滚失败，数据可以正常插入。可以自定义异常
     */
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public int save(Device device) {
        return deviceMapper.save(device);
    }

    @Override
    public List<ChainNumDTO> deviceNameJoinChainNumList(String deviceName) {
        List<ChainNumDTO> chainNumDTOList = deviceMapper.deviceNameJoinChainNumList(deviceName);
        return chainNumDTOList;
    }

    @Override
    public int deleteDeviceNumberJoinOutputJoinParam(String deviceNumber) {
        int count = deviceMapper.deleteDeviceNumberJoinOutputJoinParam(deviceNumber);
        return count;
    }

    @Override
    public PageInfo<Device> pageFindAll(int pageNum, int pageSize) {
        //这个分页方法（PageHelper）继承PageMethod 拿到startPage这个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //获取当前所有数据
        List<Device> deviceList = deviceMapper.selectAll();
        //将返回的内容放到 PageInfo中
        PageInfo<Device> page = new PageInfo<>(deviceList);
        //返回出去
        return page;
    }

    @Override
    public List<Device> limitFindAll(int pageNum, int pageSize) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("pageNum", (pageNum-1)*pageSize);
        map.put("pageSize", pageSize);
        List<Device> deviceList = deviceMapper.limitFindAll(map);
        return deviceList;
    }


}
