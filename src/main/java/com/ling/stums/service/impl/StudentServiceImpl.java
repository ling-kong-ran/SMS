package com.ling.stums.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.stums.entity.Student;
import com.ling.stums.mapper.StudentMapper;
import com.ling.stums.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 15:16
 * @Version 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {
}
