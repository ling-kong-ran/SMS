package com.ling.stums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ling.stums.dao.ClazzDao;
import com.ling.stums.dao.StudentDao;
import com.ling.stums.dto.OptionsDto;
import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.responresult.Code;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Student;
import com.ling.stums.service.ClazzService;
import com.ling.stums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:43
 * @Version 1.0
 */
@RestController
public class ClazzController {
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentDao studentDao;
    @Autowired
    ClazzDao clazzDao;

    @GetMapping("clazz")//list
    public AjaxResult<Map<String, Object>> getlist() {
        List<Clazz> list = clazzService.list();
        List<OptionsDto<Integer>> optionsDtos = new ArrayList<>();
        //用set对班级名称前缀去重
        Set<String> set = new HashSet<>();
        //原子类保证数据安全
        AtomicInteger count = new AtomicInteger(1);
        list.forEach(r -> {

            OptionsDto<Integer> options = new OptionsDto<Integer>();
            options.setValue(r.getCid());
            if (set.contains(r.getMajor())) {//当有相同专业的时候 count++
                count.getAndIncrement();
            } else {//当不是相同专业的时候 count置为1 即从1班开始
                count.set(1);
            }
            options.setLabel(r.getMajor() + count + "班");
            set.add(r.getMajor());//去重
            optionsDtos.add(options);
        });
        Map<String, Object> clazzAndOptions = new HashMap<>();
        clazzAndOptions.put("list", list);
        clazzAndOptions.put("options", optionsDtos);
        return new AjaxResult<>(Code.SUCCESS, clazzAndOptions);
    }

    @GetMapping("clazz/{key}")//selectbySname 通过学生姓名查找对应的班级
    public AjaxResult<Clazz> select(@PathVariable("key") String key) {
        Student bySname = studentDao.findBySname(key);
        //Assert.notNull(bySname,"该用户不在学生表中");
        Clazz clazz = null;
        if (bySname != null) {
            clazz = clazzDao.findById(bySname.getClz().getCid()).orElse(null);
        }
        if (!Objects.isNull(clazz)) return new AjaxResult<>(Code.SUCCESS, clazz);
        else return new AjaxResult<>(Code.ERROR, "查无此班");
    }
    @GetMapping("clazzs/{key}")//search by clazzName or major or startYears
    public AjaxResult<List<Clazz>> search(@PathVariable("key") String key) {
        List<Clazz> byClazzNameLikeOrMajorLikeOrStartYears = clazzDao.findByClazzNameLikeOrMajorLikeOrStartYears("%"+key+"%", "%"+key+"%", key);
        return new AjaxResult<>(Code.SUCCESS, byClazzNameLikeOrMajorLikeOrStartYears);
    }

    @PutMapping("clazz")//update
    public AjaxResult<Object> update(@RequestBody Clazz clazz) {
        //更新班级名称
        List<Clazz> list=clazzDao.findByMajor(clazz.getMajor());
        clazz.setClazzName(clazz.getMajor()+list.size()+"班");
        clazzDao.save(clazz);//save 底层是先判断是否存在一模一样的字段和字段数据
        return new AjaxResult<>(Code.SUCCESS);
    }

    @PostMapping("clazz")//add
    public AjaxResult<Object> add(@RequestBody Clazz clazz) {
        //更新班级名称
        List<Clazz> list=clazzDao.findByMajor(clazz.getMajor());
        clazz.setClazzName(clazz.getMajor()+(list.size())+1+"班");
        clazzDao.save(clazz);
        return new AjaxResult<>(Code.SUCCESS);
    }

    @DeleteMapping("clazz/{cid}")//delete
    public AjaxResult<Object> delete(@PathVariable("cid") Integer cid) {
        clazzService.remove(new QueryWrapper<Clazz>().eq("cid", cid));
        return new AjaxResult<>(Code.SUCCESS);
    }

}
