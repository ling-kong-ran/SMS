package com.ling.stums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.stums.entity.ClassSchedule;
import com.ling.stums.entity.Clazz;
import com.ling.stums.mapper.ClassScheduleMapper;
import com.ling.stums.mapper.ClazzMapper;
import com.ling.stums.service.ClassScheduleService;
import com.ling.stums.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:42
 * @Version 1.0
 */
@Service
public class ClassScheduleServiceImpl extends ServiceImpl<ClassScheduleMapper, ClassSchedule> implements ClassScheduleService {
    @Autowired
    ClassScheduleMapper classScheduleMapper;

}
