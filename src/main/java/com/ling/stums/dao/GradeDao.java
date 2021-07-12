package com.ling.stums.dao;

import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Course;
import com.ling.stums.entity.Grade;
import com.ling.stums.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 19:22
 * @Version 1.0
 */

public interface GradeDao extends JpaRepository<Grade,Integer> {
    List<Grade> findByStudentOrCourseAndScoreBetween(Student student, Course course, Integer score, Integer score2);
    List<Grade> findByStudentOrCourse(Student student, Course course);
    List<Grade> findByScoreBetween(Integer score, Integer score2);
    List<Grade> findByScoreAfter(Integer score);
    List<Grade> findByScoreBefore(Integer score);
    List<Grade> findByStudent(Student student);

    List<Grade> findByStudentOrCourseAndScoreBefore(Student student, Course course, Integer score);

    List<Grade> findByStudentOrCourseAndScoreAfter(Student student, Course course, Integer score);
}
