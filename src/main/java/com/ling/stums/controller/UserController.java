package com.ling.stums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.responresult.Code;
import com.ling.stums.entity.User;
import com.ling.stums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:43
 * @Version 1.0
 */
@RestController
public class UserController {
/*
* admin  admin
* 学生的用户名：默认为学生姓名
* 学生的密码：默认为123456
* */
    @Autowired
    UserService userService;

    @GetMapping("users/{key}")//listOrSearch 一个接口三个用处
    public AjaxResult<List<User>> getList(@PathVariable("key") String key){
        List<User> list;
        if (key.equals("all")){//查询所有
         list = userService.list();
        }else {//根据用户类别查询

            if ( "管理员".contains(key)|| "学生".contains(key)){
                boolean type= "管理员".contains(key);
                list=userService.list(new QueryWrapper<User>().eq("type",type));
            }else {//根据用户名模糊查询
                list=userService.selectLikeUserName(key);
            }
        }
        if (list.size()!=0) {//查询到了数据
            return new AjaxResult<>(Code.SUCCESS,list);
        }else return new AjaxResult<>(Code.ERROR,"请换个关键词试试");//未查询到任何数据
    }
    @GetMapping("user/{uid}")//select
    public AjaxResult<User> select(@PathVariable("uid") Integer uid){
       User user = userService.getOne(new QueryWrapper<User>().eq("uid",uid));
        return new AjaxResult<>(Code.SUCCESS,user);
    }
    @PutMapping("user")//update
    public AjaxResult<Object> update(@RequestBody User user){
        userService.update(user,new UpdateWrapper<User>().eq("uid",user.getUid()));
        return new AjaxResult<>(Code.SUCCESS);
    }
    @PostMapping("user")//add
    public AjaxResult<Object> add(@RequestBody User user){
        userService.saveOrUpdate(user,new QueryWrapper<User>().eq("username",user.getUsername()));
        return new AjaxResult<>(Code.SUCCESS);
    }
    @DeleteMapping("user/{uid}")//delete
    public AjaxResult<Object> delete(@PathVariable("uid") Integer uid){
        userService.remove(new QueryWrapper<User>().eq("uid",uid));
        return new AjaxResult<>(Code.SUCCESS);
    }

}
