package com.ling.stums.dao;

import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Grade;
import com.ling.stums.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 19:22
 * @Version 1.0
 */

public interface StudentDao extends JpaRepository<Student,Integer> {
    Student findBySname(String sname);
    // 根据学号查询、根据姓名查询某个学生信息，
    // 根据专业、班级查询某个专业、班级所有学生信息
    List<Student> findBySnoOrSnameOrMajorOrClz(Integer sno, String sname, String major, Clazz clz);

    List<Student> findBySnoOrSnameLikeOrMajorLikeOrClzLike(int parseInt, String key, String key1, Clazz setInstructorName);

    Student findBySno(int integer);

    List<Student> findBySnameLikeOrMajorLikeOrClzLike(String sname, String major, Clazz clz);

    List<Student> findBySnoOrClz(Integer sno, Clazz clz);

    List<Student> findByClzOrSno(Clazz setPopulation, int integer);

    List<Student> findByClzLikeOrSnameLikeOrMajorLike(Clazz setStartYears, String s, String s1);

    List<Student> findBySnoOrSname(Integer sno, String sname);
}
