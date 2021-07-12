package com.ling.stums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.stums.entity.User;
import com.ling.stums.mapper.UserMapper;
import com.ling.stums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:42
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> selectLikeUserName(String username) {
        return userMapper.searchByName(username);
    }
}
