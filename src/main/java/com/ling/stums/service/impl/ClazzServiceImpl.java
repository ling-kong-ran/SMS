package com.ling.stums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.stums.dao.ClazzDao;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.User;
import com.ling.stums.mapper.ClazzMapper;
import com.ling.stums.mapper.UserMapper;
import com.ling.stums.service.ClazzService;
import com.ling.stums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:42
 * @Version 1.0
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
    @Autowired
    ClazzDao clazzDao;

    @Autowired
    ClazzMapper clazzMapper;
    public Clazz findClazzAndStudents(Integer cid){
        return clazzDao.findById(cid).orElse(null);
    }

    @Override
    public void updatePopulation(Integer cid) {
        clazzMapper.updatePopulation(cid);
    }
}
