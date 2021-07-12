package com.ling.stums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.stums.dao.UserDao;
import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.LoginDto;
import com.ling.stums.dto.responresult.Code;
import com.ling.stums.entity.User;
import com.ling.stums.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:44
 * @Version 1.0
 */
@RestController
public class AccountController {//其他模块 主要是登录和修改密码
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    @PostMapping("/login")//登录成功返回userInfo和roles
    public AjaxResult<LoginDto> login(@RequestBody User user) {
        User one = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        Assert.notNull(one, "用户不存在");//断言判断查询是否为空
        if (!one.getPassword().equals(user.getPassword())) {
            return new AjaxResult<>(Code.ERROR);
        }
        LoginDto dto = new LoginDto();
        BeanUtils.copyProperties(one, dto);
        if (one.getType()) dto.setRoles("admin");
        else dto.setRoles("editor");
        return new AjaxResult<>(Code.SUCCESS, dto);
    }

    /*
     *Vue封装的user对象中只需要设置用户名和密码即可  其他的不用管 后台防止为空即可
     * 防止为空的方案：先根据用户名查询该用户信息再用user的密码去覆盖掉从数据库中查询的的用户即可
     * */
    @PostMapping("/password")
    public AjaxResult<Object> updatePassword(@RequestBody User user) {
        User byUsername = userDao.findByUsername(user.getUsername());
        if (Objects.equals(byUsername.getPassword(), user.getPassword())){
            return new AjaxResult<>(Code.ERROR,"请重新输入密码");
        }else
        byUsername.setPassword(user.getPassword());
        userDao.save(user);
        return new AjaxResult<>(Code.SUCCESS);
    }


}
