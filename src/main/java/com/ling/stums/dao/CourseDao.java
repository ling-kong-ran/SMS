package com.ling.stums.dao;

import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 19:22
 * @Version 1.0
 */

public interface CourseDao extends JpaRepository<Course,Integer> {
    Course findByCourseName(String courseName);

    List<Course> findByCourseNameLike(String courseName);
}
