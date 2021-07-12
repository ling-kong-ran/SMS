package com.ling.stums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.stums.dao.ClassScheduleDao;
import com.ling.stums.dao.ClazzDao;
import com.ling.stums.dao.CourseDao;
import com.ling.stums.dto.ClassScheduleDto;
import com.ling.stums.dto.ClassScheduleRequestBodyDto;
import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.responresult.Code;
import com.ling.stums.entity.ClassSchedule;
import com.ling.stums.entity.Clazz;
import com.ling.stums.entity.Course;
import com.ling.stums.service.ClassScheduleService;
import com.ling.stums.utils.ClassScheduleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 12:43
 * @Version 1.0
 */
@RestController
public class ClassScheduleController {
    @Autowired
    ClassScheduleService classScheduleService;
    @Autowired
    ClassScheduleDao classScheduleDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    ClazzDao clazzDao;

    @GetMapping("cs")//list
    public AjaxResult<List<ClassSchedule>> getlist() {
        List<ClassSchedule> list = classScheduleService.list();
        return new AjaxResult<>(Code.SUCCESS, list);
    }

    @GetMapping("cs/{sid}")//selectOneBySid
    public AjaxResult<ClassSchedule> select(@PathVariable("sid") Integer sid) {
        ClassSchedule classSchedule = classScheduleService.getOne(new QueryWrapper<ClassSchedule>().eq("sid", sid));
        return new AjaxResult<>(Code.SUCCESS, classSchedule);
    }

    @GetMapping("cssearch/{searchKey}")//selectClazzlistlike
    public AjaxResult<List<ClassSchedule>> searchList(@PathVariable("searchKey") String key,Integer cid) {
        List<ClassSchedule> list = new ArrayList<>();
        //假设key1为输入框的输入内容key1  key2为右边两个下拉框组成的内容key2
        String[] split = key.split("@");//key1@key2
        if (key.startsWith("@")) {//只查询时间 即key2查询 例如@7-2就是查询周日第二大节课
            String substring = key.substring(1);//去掉@-> 7-2
            String[] split1 = substring.split("-");
            if (substring.startsWith("-")) {//@-2 %2 根据某一节课 比如这里就是第二节课 搜索
                list = classScheduleDao.findByTeachTimeLikeAndClz("%" + split1[1],new Clazz().setCid(cid));
            } else {//7-2 或2-
                //@2-   2% 根据周某节课查询  例如2- 就是查询第二周的课
                if (split1.length == 1) list = classScheduleDao.findByTeachTimeLikeAndClz(split1[0] + "%",new Clazz().setCid(cid));
                    //或者7-1 这个时候就要根据时间准确查询啦
                else list = classScheduleDao.findByTeachTimeLikeAndClz(substring,new Clazz().setCid(cid));
            }
        } else {//（既有key1 又有key2 或者只查询key1 此时String数组为： [key1,key2]）  或者  （只查询key1 ）[key1]
            Integer cno = split[0].toCharArray().length == 1 ? Integer.parseInt(split[0]) : -1;
            if (split.length == 1) {//只查询key1 此时例如为武则天@- 就是查询所有武则天老师的课程  即根据课程号（精确查询）或者课程名（模糊查询）或者老师（模糊查询）
                list = classScheduleDao.findByCourseLikeOrTeacherLikeAndClz(new Course().setCno(cno).setCourseName("%" + split[0] + "%"), "%" + split[0] + "%",new Clazz().setCid(cid));
            } else if (split.length == 2) {//查询key1和key2  双条件搜索之间和连接 根据 条件1（课程号或者课程名或者老师）和 条件2（授课时间）
                //而对于key1没有闲置即根据课程号或者课程名或者老师查询 而key1如果为数字则为根据课程号精确查询查询（这个不能模糊查询） 如果是其他的就又有两种可能：1.根据课程名查 2根据老师查询 而这两者可以都通过模糊查询并且用或连接起来
                //对于key2授课时间又可分为只查询周几的课 或者查询是第某节课的  或者查询周几某节课的 以下三种情况
                //第一种只查询周几的课 7-  （周日的）
                //第二种只查询某节课的课 -1  （第一大节课的）
                //第三种只查询某天某节课的课 7-1  （周日的第一大节课的）
                String[] split1 = split[1].split("-");//对key2进行划分
                if (split1.length==0){//只根据key1查询
                    /*
                     * 注：这里的key1又可能分为二种类型第一种数字，代表是课程号，第二种是string 代表是课程名或者老师
                     * */
                    if (cno==-1){//此时key1不为数字 此时key1为课程名或者老师
                        //先根据课程名查询课程信息 如果查的到key1就 为课程名  查不到就为老师信息
                        Course byCourseName = courseDao.findByCourseName(split[0]);
                        if (Objects.isNull(byCourseName)){//此时key1为老师
                            list=classScheduleDao.findByTeacherAndClz(split[0],new Clazz().setCid(cid));

                        }else {//此时key1为课程名
                            list=classScheduleDao.findByCourseAndClz(new Course().setCno(byCourseName.getCno()),new Clazz().setCid(cid));
                        }

                    }else {//此时key1为数字 即此时key1为课程号
                        list=classScheduleDao.findByCourseAndClz(new Course().setCno(cno),new Clazz().setCid(cid));
                    }
                   // list=classScheduleDao.findByCourseLikeOrTeacherLike(new Course().setCno(cno).setCourseName(split[0]),split[0]);
                }else {//查询key1和key2
                    /*
                     * 注：这里的key1又可能分为二种类型第一种数字，代表是课程号，第二种是string 代表是课程名或者老师
                     * */
                    if (cno==-1){//此时key1不为数字 此时key1为课程号或者老师
                        Course byCourseName = courseDao.findByCourseName(split[0]);
                        if (Objects.isNull(byCourseName)){//此时key1为老师
                            if (split[1].startsWith("-")){//-1 注: 这是指前台传过来的key
                                list=classScheduleDao.findByTeacherLikeAndTeachTimeLikeAndClz( "%" + split[0] + "%", "%"+split[1],new Clazz().setCid(cid));
                            }else {// 7- ->7 或7-1 ->[7,1]
                                if (split1.length==1)  list = classScheduleDao.findByTeacherLikeAndTeachTimeLikeAndClz("%" + split[0] + "%", split[1]+"%",new Clazz().setCid(cid));
                                else list = classScheduleDao.findByTeacherLikeAndTeachTimeLikeAndClz("%" + split[0] + "%", split[1],new Clazz().setCid(cid));
                            }

                        }else {//此时key1为课程名
                            if (split[1].startsWith("-")){//-1 注: 这是指前台传过来的key
                                list=classScheduleDao.findByCourseAndTeachTimeLikeAndClz( new Course().setCno(byCourseName.getCno()), "%"+split[1],new Clazz().setCid(cid));
                            }else {// 7- ->7 或7-1 ->[7,1]
                                if (split1.length==1)  list = classScheduleDao.findByCourseAndTeachTimeLikeAndClz( new Course().setCno(byCourseName.getCno()), split[1]+"%",new Clazz().setCid(cid));
                                else list = classScheduleDao.findByCourseAndTeachTimeLikeAndClz(new Course().setCno(byCourseName.getCno()), split[1],new Clazz().setCid(cid));
                            }
                        }
                    }else {//此时key1为数字 即此时key1为课程号
//对key2的筛
                        if (split[1].startsWith("-")){//-1 注: 这是指前台传过来的key
                            list=classScheduleDao.findByCourseAndTeachTimeLikeAndClz(new Course().setCno(cno),  "%"+split[1],new Clazz().setCid(cid));
                        }else {// 7- ->7 或7-1 ->[7,1]
                            if (split1.length==1)  list = classScheduleDao.findByCourseAndTeachTimeLikeAndClz(new Course().setCno(cno), split[1]+"%",new Clazz().setCid(cid));
                            else list = classScheduleDao.findByCourseAndTeachTimeLikeAndClz(new Course().setCno(cno),  split[1],new Clazz().setCid(cid));
                        }
                    }

                }

            }
        }
        if (list.size()>0)return new AjaxResult<>(Code.SUCCESS, list);
        else return new AjaxResult<>(Code.ERROR,"未查询到数据");
    }

    @GetMapping("css/{cid}")//select某个班的课程表信息
    public AjaxResult<List<ClassScheduleDto>> selectCs(@PathVariable("cid") Integer cid) {
        List<ClassSchedule> allByClz = classScheduleDao.findAllByClz(new Clazz().setCid(cid));
        //ClassScheduleDto classScheduleDto=new ClassScheduleDto();
        List<ClassScheduleDto> classScheduleDtos = ClassScheduleUtil.dispachClazzClassSchedule(allByClz);
        return new AjaxResult<>(Code.SUCCESS, classScheduleDtos);
    }

    @PutMapping("cs")//update
    public AjaxResult<Object> update(@RequestBody ClassScheduleRequestBodyDto classSchedule) {
        ClassSchedule classS = new ClassSchedule();
        Clazz clazz=clazzDao.findById(classSchedule.getCid()).orElse(null);
        Course course = courseDao.findById(classSchedule.getCno()).orElse(null);
        classS.setClz(clazz)
                .setCourse(course)
                .setTeacher(classSchedule.getTeacher())
                .setTeachPlace(classSchedule.getTeachPlace())
                .setTeachTime(classSchedule.getTeachTime())
                .setSid(classSchedule.getSid());
        classScheduleDao.save(classS);
        //classScheduleService.update(classSchedule,new UpdateWrapper<ClassSchedule>().eq("sid",classSchedule.getSid()));
        return new AjaxResult<>(Code.SUCCESS);
    }

    @PostMapping("cs")//add
    public AjaxResult<Object> add(@RequestBody ClassScheduleRequestBodyDto classSchedule) {
        ClassSchedule classS = new ClassSchedule();
        Clazz clazz=clazzDao.findById(classSchedule.getCid()).orElse(null);
        Course course = courseDao.findById(classSchedule.getCno()).orElse(null);
        classS.setClz(clazz)
                .setCourse(course)
                .setTeacher(classSchedule.getTeacher())
                .setTeachPlace(classSchedule.getTeachPlace())
                .setTeachTime(classSchedule.getTeachTime().substring(1, 4))
                .setSid(-1);//hiebernate主键策略 这里随便给个值插入数据库的就是根据自己设置的自增策略形成的
        Assert.isTrue(classScheduleDao.findByTeachTimeAndTeachPlace(classS.getTeachTime(), classS.getTeachPlace()) == null, "课表添加失败");
        classScheduleDao.save(classS);
        return new AjaxResult<>(Code.SUCCESS);
    }

    @DeleteMapping("cs/{sid}")//delete
    public AjaxResult<Object> delete(@PathVariable("sid") Integer sid) {
        classScheduleService.remove(new QueryWrapper<ClassSchedule>().eq("sid", sid));
        return new AjaxResult<>(Code.SUCCESS);
    }

}
