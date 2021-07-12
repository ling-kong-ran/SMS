package com.ling.stums.utils;

import com.ling.stums.dto.ClassScheduleDto;
import com.ling.stums.entity.ClassSchedule;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/12/1 0001 19:56
 * @Version 1.0
 */
public class ClassScheduleUtil {
    static public List<ClassScheduleDto> dispachClazzClassSchedule(List<ClassSchedule> classSchedules){
        List<ClassScheduleDto> classScheduleDtos=new ArrayList<>(4);
        ClassScheduleDto classScheduleDto=new ClassScheduleDto();
        ClassScheduleDto classScheduleDto1=new ClassScheduleDto();
        ClassScheduleDto classScheduleDto2=new ClassScheduleDto();
        ClassScheduleDto classScheduleDto3=new ClassScheduleDto();
        //从数据库中获取
        classSchedules.forEach(r->{
            String teachTime = r.getTeachTime();
            String[] split = teachTime.split("-");
            Assert.isTrue(split.length==2,"授课时间格式不正确");
            if (split[1].equals("1")) {//第一节课
                if (split[0].equals("1")){//周一
                    classScheduleDto.setN1(r);
                }else if (split[0].equals("2")){//周二
                    classScheduleDto.setN2(r);
                }
                else if (split[0].equals("3")){//周三
                    classScheduleDto.setN3(r);
                }
                else if (split[0].equals("4")){//周四
                    classScheduleDto.setN4(r);
                }
                else if (split[0].equals("5")){//周五
                    classScheduleDto.setN5(r);
                }
                else if (split[0].equals("6")){//周六
                    classScheduleDto.setN6(r);
                }
                else if (split[0].equals("7")){//周七
                    classScheduleDto.setN7(r);
                }
            }else if (split[1].equals("2")){//第二节课
                if (split[0].equals("1")){//周一
                    classScheduleDto1.setN1(r);
                }else if (split[0].equals("2")){//周二
                    classScheduleDto1.setN2(r);
                }
                else if (split[0].equals("3")){//周三
                    classScheduleDto1.setN3(r);
                }
                else if (split[0].equals("4")){//周四
                    classScheduleDto1.setN4(r);
                }
                else if (split[0].equals("5")){//周五
                    classScheduleDto1.setN5(r);
                }
                else if (split[0].equals("6")){//周六
                    classScheduleDto1.setN6(r);
                }
                else if (split[0].equals("7")){//周七
                    classScheduleDto1.setN7(r);
                }
            }else if (split[1].equals("3")){//第三节课
                if (split[0].equals("1")){//周一
                    classScheduleDto2.setN1(r);
                }else if (split[0].equals("2")){//周二
                    classScheduleDto2.setN2(r);
                }
                else if (split[0].equals("3")){//周三
                    classScheduleDto2.setN3(r);
                }
                else if (split[0].equals("4")){//周四
                    classScheduleDto2.setN4(r);
                }
                else if (split[0].equals("5")){//周五
                    classScheduleDto2.setN5(r);
                }
                else if (split[0].equals("6")){//周六
                    classScheduleDto2.setN6(r);
                }
                else if (split[0].equals("7")){//周七
                    classScheduleDto2.setN7(r);
                }
            }else if (split[1].equals("4")){//第四节课
                if (split[0].equals("1")){//周一
                    classScheduleDto3.setN1(r);
                }else if (split[0].equals("2")){//周二
                    classScheduleDto3.setN2(r);
                }
                else if (split[0].equals("3")){//周三
                    classScheduleDto3.setN3(r);
                }
                else if (split[0].equals("4")){//周四
                    classScheduleDto3.setN4(r);
                }
                else if (split[0].equals("5")){//周五
                    classScheduleDto3.setN5(r);
                }
                else if (split[0].equals("6")){//周六
                    classScheduleDto3.setN6(r);
                }
                else if (split[0].equals("7")){//周七
                    classScheduleDto3.setN7(r);
                }
            }
        });
        classScheduleDtos.add(classScheduleDto);
        classScheduleDtos.add(classScheduleDto1);
        classScheduleDtos.add(classScheduleDto2);
        classScheduleDtos.add(classScheduleDto3);
        return classScheduleDtos;
    }

}
