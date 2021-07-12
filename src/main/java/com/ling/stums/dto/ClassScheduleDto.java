package com.ling.stums.dto;

import com.ling.stums.entity.ClassSchedule;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lingkongran
 * @Date 2020/12/1 0001 18:37
 * @Version 1.0
 */
public class ClassScheduleDto {
    //周一到周日
    private ClassSchedule n1;
    private ClassSchedule n2;
    private ClassSchedule n3;
    private ClassSchedule n4;
    private ClassSchedule n5;
    private ClassSchedule n6;
    private ClassSchedule n7;

    public ClassScheduleDto() {
    }

    public ClassScheduleDto(ClassSchedule n1, ClassSchedule n2, ClassSchedule n3, ClassSchedule n4, ClassSchedule n5, ClassSchedule n6, ClassSchedule n7) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.n5 = n5;
        this.n6 = n6;
        this.n7 = n7;
    }

    public ClassSchedule getN1() {
        return n1;
    }

    public void setN1(ClassSchedule n1) {
        this.n1 = n1;
    }

    public ClassSchedule getN2() {
        return n2;
    }

    public void setN2(ClassSchedule n2) {
        this.n2 = n2;
    }

    public ClassSchedule getN3() {
        return n3;
    }

    public void setN3(ClassSchedule n3) {
        this.n3 = n3;
    }

    public ClassSchedule getN4() {
        return n4;
    }

    public void setN4(ClassSchedule n4) {
        this.n4 = n4;
    }

    public ClassSchedule getN5() {
        return n5;
    }

    public void setN5(ClassSchedule n5) {
        this.n5 = n5;
    }

    public ClassSchedule getN6() {
        return n6;
    }

    public void setN6(ClassSchedule n6) {
        this.n6 = n6;
    }

    public ClassSchedule getN7() {
        return n7;
    }

    public void setN7(ClassSchedule n7) {
        this.n7 = n7;
    }
    /*
    * pram classSchedules 根据班级查询到的所有课程表信息
    * 授课时间 4-2 代表星期四第二节课 以此类推
    * */
}
