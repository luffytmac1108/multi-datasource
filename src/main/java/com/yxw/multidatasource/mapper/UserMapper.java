package com.yxw.multidatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxw.multidatasource.entity.UserData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserData> {

}
