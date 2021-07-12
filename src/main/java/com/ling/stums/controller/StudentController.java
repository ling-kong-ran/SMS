package com.ling.stums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ling.stums.dao.ClazzDao;
import com.ling.stums.dao.StudentDao;
import com.ling.stums.dao.UserDao;
import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.responresult.Code;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Student;
import com.ling.stums.entity.User;
import com.ling.stums.service.ClazzService;
import com.ling.stums.service.StudentService;
import com.ling.stums.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:43
 * @Version 1.0
 */
@RestController
public class StudentController {
    final String DEFAULTPASSWORD="123456";
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentDao studentDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ClazzDao clazzDao;

    @GetMapping("student")//list
    public AjaxResult<List<Student>> getlist(){
        List<Student> all = studentDao.findAll();
        return new AjaxResult<>(Code.SUCCESS,all);
    }
    @GetMapping("student/{key}")//selectOneByIdOrSname
    public AjaxResult<Student> select(@PathVariable("key") String key){
        char[] chars = key.toCharArray();
        AtomicInteger atomicInteger=new AtomicInteger(0);
        /*
         * 判断该key是否为数字
         * */
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) atomicInteger.getAndIncrement();
        }
        if (Integer.parseInt(String.valueOf(atomicInteger))==chars.length){
            Student bySno = studentDao.findBySno(Integer.parseInt(key));
            return new AjaxResult<>(Code.SUCCESS, bySno) ;
        }else {
            Student bySname = studentDao.findBySname(key);
            return new AjaxResult<>(Code.SUCCESS, bySname);
        }
    }
    @GetMapping("students/{key}")//searchList
    // 根据学号查询、根据姓名查询某个学生信息，
    // 根据专业、班级查询某个专业、班级所有学生信息
    public AjaxResult<List<Student>> search(@PathVariable("key") String key){
        char[] chars = key.toCharArray();
        AtomicInteger atomicInteger=new AtomicInteger(0);
        /*
        * 判断该key是否为数字
        * */
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) atomicInteger.getAndIncrement();
        }
        if (Integer.parseInt(String.valueOf(atomicInteger))==chars.length){
            int integer = Integer.parseInt(key);
            //根据学号或者班级号或者查询一切是数字的东西
            Clazz setPopulation = new Clazz().setCid(integer).setPopulation(integer);
            List<Student> bySno = studentDao.findByClzOrSno(setPopulation,integer);
            return new AjaxResult<>(Code.SUCCESS,bySno);
        }else{
            Clazz setStartYears = new Clazz().setInstructorName("%" + key + "%").setClazzName("%" + key + "%").setCid(-1);
            List<Student> bySnameLikeOrMajorLikeOrClzLike = studentDao.findByClzLikeOrSnameLikeOrMajorLike(setStartYears,"%" + key + "%", "%" + key + "%");
            return new AjaxResult<>(Code.SUCCESS, bySnameLikeOrMajorLikeOrClzLike);
        }
    }

    @PutMapping("student")//update
    public AjaxResult<Object> update(@RequestBody Student student){
        //更新班级信息
        Clazz byId = clazzDao.findById(student.getClz().getCid()).orElse(null);
        student.setClz(byId);//防止前台json对象污染
        // 因为Vue封装的对象和后台一致而有些修改并没有把非必须的属性给一同修改掉了
        // 既然是外键就可以直接在数据库中先获取再用正确信息去覆盖掉即可
        //如果修改了学生的姓名的话对应也将用户信息修改
        studentDao.save(student);
        User byUsername = userDao.findByUsername(student.getSname());
        if (Objects.isNull(byUsername)){
            //说明修改了学生的姓名
            //对应也要该用户表新增
            userDao.save(new User().setUid(-1).setPassword(DEFAULTPASSWORD).setType(false).setUsername(student.getSname()));
        }
        return new AjaxResult<>(Code.SUCCESS);
    }
    @PostMapping("student")//add
    public AjaxResult<Object> add(@RequestBody Student student){
        //添加学生的时候 如果用户表中不存在该学生的信息直接在用户表中插入一份数据，用户名默认为姓名 密码默认为123456 类型自然为非管理员类型
        User byUsername = userDao.findByUsername(student.getSname());
        if (Objects.isNull(byUsername)){//用户表中不存在该学生信息的时候才在用户表中添加该学生用户信息
            User user=new User().setUid(-1).setUsername(student.getSname()).setPassword(DEFAULTPASSWORD).setType(false);
            userDao.save(user);
        }//用户表中存在该学生信息不需要在用户表中添加该学生的用户信息
        Clazz clazz = clazzDao.findById(student.getClz().getCid()).orElse(null);
        student.setClz(clazz);
        studentDao.save(student);
        return new AjaxResult<>(Code.SUCCESS);
    }
    @SneakyThrows
    @DeleteMapping("student/{sno}")//delete
    public AjaxResult<Object> delete(@PathVariable("sno") Integer sno){
        Student byId =studentDao.findById(sno).orElse(null);
        //删除对应的用户信息
        assert byId != null;
        User byUsername = userDao.findByUsername(byId.getSname());
        userDao.delete(byUsername);
        //最后在删除学生信息
        studentDao.delete(byId);
        return new AjaxResult<>(Code.SUCCESS);
    }

}
