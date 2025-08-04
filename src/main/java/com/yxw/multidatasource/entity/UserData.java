package com.yxw.multidatasource.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user_data")
public class UserData {

    @TableId
    private Integer id;

    private String username;

    private String email;

    private LocalDateTime createdAt;
}
