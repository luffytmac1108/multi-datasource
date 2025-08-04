package com.yxw.multidatasource.controller;

import com.yxw.multidatasource.entity.UserData;
import com.yxw.multidatasource.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/mysql")
    public List<UserData> listMysqlUsers() {
        return userService.getMysqlUsers();
    }

    @GetMapping("/postgre")
    public List<UserData> listPostgreUsers() {
        return userService.getPostgreUsers();
    }
}
