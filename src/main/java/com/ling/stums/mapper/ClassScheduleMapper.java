package com.ling.stums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ling.stums.entity.ClassSchedule;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 15:16
 * @Version 1.0
 */
public interface ClassScheduleMapper extends BaseMapper<ClassSchedule> {
//    List<ClassSchedule> searchByClass(@Param("s") String s);
//
//    List<ClassSchedule> searchByDay(@Param("s") String s);
//    List<ClassSchedule> findByCourseOrTeacherOrTeacher(String key);//根据课程号或者课程名或者老师查询
//    List<ClassSchedule> findByCourseOrTeacherOrTeacherAndTeachTime(String key1,String key2);//根据课程号或者课程名或者老师和授课老师查询
//    List<ClassSchedule> findByTeachTime(String key);//根据授课时间查询
}
