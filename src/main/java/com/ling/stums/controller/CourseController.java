package com.ling.stums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ling.stums.dao.CourseDao;
import com.ling.stums.dto.OptionsDto;
import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.responresult.Code;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Course;
import com.ling.stums.entity.Grade;
import com.ling.stums.service.CourseService;
import com.ling.stums.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:43
 * @Version 1.0
 */
@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseDao courseDao;

    @GetMapping("course")//list
    public AjaxResult<Map<String ,Object>> getlist(){
        List<Course> list = courseService.list();
        List<OptionsDto<Integer>> options = new ArrayList<>();

        list.forEach(r->{
            OptionsDto<Integer> optionsDto=new OptionsDto<>();
            optionsDto.setLabel(r.getCourseName());
            optionsDto.setValue(r.getCno());
            options.add(optionsDto);
        });
        Map<String ,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("options",options);
        return new AjaxResult<>(Code.SUCCESS,map);
    }
    @GetMapping("course/{key}")//select根据课程号或课程名查询
    public AjaxResult<List<Course>> select(@PathVariable("key") String key){
        char[] chars = key.toCharArray();
        AtomicInteger count =new AtomicInteger(0);
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) count.getAndIncrement();
        }
        List<Course> res;
        if (Integer.parseInt(String.valueOf(count))==chars.length){
            //课程号
            res=courseService.list(new QueryWrapper<Course>().eq("cno",key));
        }else res=courseDao.findByCourseNameLike("%"+key+"%");
            return new AjaxResult<>(Code.SUCCESS,res);
    }
    @PutMapping("course")//update
    public AjaxResult<Object> update(@RequestBody Course course){
        courseService.update(course,new UpdateWrapper<Course>().eq("cno",course.getCno()));
        return new AjaxResult<>(Code.SUCCESS);
    }
    @PostMapping("course")//add
    public AjaxResult<Object> add(@RequestBody Course course){
        courseService.saveOrUpdate(course,new QueryWrapper<Course>().eq("course_name",course.getCourseName()));
        return new AjaxResult<>(Code.SUCCESS);
    }
    @DeleteMapping("course/{cno}")//delete
    public AjaxResult<Object> delete(@PathVariable("cno") Integer cno){
        courseService.remove(new QueryWrapper<Course>().eq("cno",cno));
        return new AjaxResult<>(Code.SUCCESS);
    }

}
