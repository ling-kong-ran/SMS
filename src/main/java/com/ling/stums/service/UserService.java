package com.ling.stums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.stums.entity.User;
import com.ling.stums.mapper.UserMapper;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:41
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    List<User> selectLikeUserName(String username);
}
