package com.ling.stums.dao;

import com.ling.stums.entity.Student;
import com.ling.stums.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 19:22
 * @Version 1.0
 */

public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
