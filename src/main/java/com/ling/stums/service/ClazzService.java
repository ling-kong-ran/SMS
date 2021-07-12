package com.ling.stums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.stums.entity.Clazz;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 15:16
 * @Version 1.0
 */
public interface ClazzService extends IService<Clazz> {
     Clazz findClazzAndStudents(Integer cid);
    void updatePopulation (Integer cid);
}
