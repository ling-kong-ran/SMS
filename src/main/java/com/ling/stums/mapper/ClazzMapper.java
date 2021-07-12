package com.ling.stums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Student;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 15:16
 * @Version 1.0
 */
public interface ClazzMapper extends BaseMapper<Clazz> {
    void updatePopulation (Integer cid);
}
