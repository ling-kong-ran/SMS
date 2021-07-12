package com.ling.stums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ling.stums.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 10:51
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> searchByName(String username);//根据用户名模糊查询

}
