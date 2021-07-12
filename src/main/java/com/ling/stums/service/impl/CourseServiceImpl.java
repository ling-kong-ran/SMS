package com.ling.stums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Course;
import com.ling.stums.mapper.ClazzMapper;
import com.ling.stums.mapper.CourseMapper;
import com.ling.stums.service.ClazzService;
import com.ling.stums.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:42
 * @Version 1.0
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
}
