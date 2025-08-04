package com.yxw.multidatasource.service;

import com.yxw.multidatasource.config.TargetDataSource;
import com.yxw.multidatasource.entity.UserData;
import com.yxw.multidatasource.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @TargetDataSource("mysql")
    public List<UserData> getMysqlUsers() {
        return userMapper.selectList(null);
    }

    @TargetDataSource("postgresql")
    public List<UserData> getPostgreUsers() {
        return userMapper.selectList(null);
    }
}