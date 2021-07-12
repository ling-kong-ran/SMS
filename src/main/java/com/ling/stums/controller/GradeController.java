package com.ling.stums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.stums.dao.GradeDao;
import com.ling.stums.dao.StudentDao;
import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.responresult.Code;
import com.ling.stums.entity.Course;
import com.ling.stums.entity.Grade;
import com.ling.stums.entity.Student;
import com.ling.stums.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:43
 * @Version 1.0
 */
@RestController
public class GradeController {
    @Autowired
    GradeService gradeService;
    @Autowired
    GradeDao gradeDao;
    @Autowired
    StudentDao studentDao;
    @GetMapping("grade")//list
    public AjaxResult<List<Grade>> getlist() {
        List<Grade> all = gradeDao.findAll();
        return new AjaxResult<>(Code.SUCCESS, all);
    }

    @GetMapping("grades/{sname}")//select gradeList by student'sname
    public AjaxResult<List<Grade>> select(@PathVariable("sname") String sname) {
        Student bySname = studentDao.findBySname(sname);
        List<Grade> byStudent = gradeDao.findByStudent(bySname);
        return new AjaxResult<>(Code.SUCCESS, byStudent);
    }

    /*
     * @parm key 学号或者课程号
     * @parm beginScore 成绩区间查询头
     * @parm endScore 成绩区间查询尾
     * */
    // 前台传的beginScore和endScore为-1 代表没有进行区间查询
    // 前台传的key为-1 代表没有进行区间学号或课程号
    @GetMapping("grade/{key}/{beginScore}/{endScore}")//select
    public AjaxResult<List<Grade>> searchList(@PathVariable("key") Integer key,@PathVariable("beginScore") Integer beginScore,@PathVariable("endScore") Integer endScore) {
        List<Grade> res;
        if (key != -1 && (beginScore != -1 || endScore != -1)) {//即按照学号或课程号查询  并且按照区间查询
            if (beginScore==-1){//<
                //这里学号和课程号没什么好办法区分开 后期会对学号格式重新编排
                res=gradeDao.findByStudentOrCourseAndScoreBefore(new Student().setSno(key),new Course().setCno(key),endScore);
            }else if (endScore==-1){//>
                res=gradeDao.findByStudentOrCourseAndScoreAfter(new Student().setSno(key),new Course().setCno(key),beginScore);
            }else {//between
                res=gradeDao.findByStudentOrCourseAndScoreBetween(new Student().setSno(key),new Course().setCno(key),beginScore,endScore);
            }
        }else {//其中一个
            if (key==-1){//只按照区间查询
                if (beginScore==-1){//<
                    res=gradeDao.findByScoreBefore(endScore);
                }else if (endScore==-1){//>
                    res=gradeDao.findByScoreAfter(beginScore);
                }else {//between
                    res=gradeDao.findByScoreBetween(beginScore,endScore);
                }
            }else {//只按照课程号或学号查询
                res=gradeDao.findByStudentOrCourse(new Student().setSno(key),new Course().setCno(key));
            }
        }
        return new AjaxResult<>(Code.SUCCESS,res);
    }

    @PutMapping("grade")//update
    public AjaxResult<Object> update(@RequestBody Grade grade) {
        gradeDao.save(grade);
        return new AjaxResult<>(Code.SUCCESS);
    }

    @PostMapping("grade")//add
    public AjaxResult<Object> add(@RequestBody Grade grade) {
        gradeDao.save(grade);
        return new AjaxResult<>(Code.SUCCESS);
    }

    @DeleteMapping("grade/{gid}")//delete
    public AjaxResult<Object> delete(@PathVariable("gid") Integer gid) {
        gradeService.remove(new QueryWrapper<Grade>().eq("gid", gid));
        return new AjaxResult<>(Code.SUCCESS);
    }

}
