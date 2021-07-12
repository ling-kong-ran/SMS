package com.ling.stums.dao;

import com.ling.stums.entity.ClassSchedule;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sun.misc.Cache;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 19:22
 * @Version 1.0
 */

public interface ClassScheduleDao extends JpaRepository<ClassSchedule,Integer> {
    List<ClassSchedule> findAllByClz(Clazz clazz);
    ClassSchedule findByTeachTimeAndTeachPlace(String teachTime,String teachPlace);
    List<ClassSchedule> findByCourseOrTeacher(Course course, String teacher);//根据课程号或者课程名或者老师查询
    List<ClassSchedule> findByCourseLikeOrTeacherLikeAndTeachTimeLike(Course course, String teacher,String teachTime);//根据课程号或者课程名或者老师和授课老师查询
    List<ClassSchedule> findByCourseLikeOrTeacherLikeAndTeachTime(Course course, String teacher,String teachTime);//根据课程号或者课程名或者老师和授课老师查询
    List<ClassSchedule> findByTeachTime(String key);//根据授课时间查询
    List<ClassSchedule> findByTeachTimeLike(String teacherTime);
    List<ClassSchedule> findByCourseLike(Course course);
    List<ClassSchedule> findByCourseLikeOrTeacherLike(Course course,String TeacherLike);

    List<ClassSchedule> findByCourse(Course setCno);

    List<ClassSchedule> findByTeacher(String s);

    List<ClassSchedule> findByCourseAndTeachTimeLike(Course course, String teachTime);

    List<ClassSchedule> findByCourseAndTeachTime(Course setCno, String s);

    List<ClassSchedule> findByTeacherLikeAndTeachTimeLike(String s, String s1);

    List<ClassSchedule> findByTeacherLikeAndTeachTime(String s, String s1);

    List<ClassSchedule> findByTeachTimeLikeAndClz(String teachTime, Clazz clz);

    List<ClassSchedule> findByCourseLikeOrTeacherLikeAndClz(Course course, String teacher, Clazz clz);

    List<ClassSchedule> findByTeacherAndClz(String teacher, Clazz clz);

    List<ClassSchedule> findByCourseAndClz(Course course, Clazz clz);

    List<ClassSchedule> findByTeacherLikeAndTeachTimeLikeAndClz(String teacher, String teachTime, Clazz clz);

    List<ClassSchedule> findByCourseAndTeachTimeLikeAndClz(Course course, String teachTime, Clazz clz);
}
