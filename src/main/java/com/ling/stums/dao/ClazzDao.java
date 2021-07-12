package com.ling.stums.dao;

import com.ling.stums.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 19:22
 * @Version 1.0
 */

public interface ClazzDao extends JpaRepository<Clazz,Integer> {
    List<Clazz> findByClazzNameLikeOrMajorLikeOrStartYears(String clazzName, String major, String startYears);

    List<Clazz> findByMajor(String major);
}
