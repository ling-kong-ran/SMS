package com.ling.stums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @Author Lingkongran
 * @Date 2020/11/27 0027 23:39
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class ClassSchedule {//课程表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer sid;//排课id


    //外键:班级号
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "cno")
    @TableField(exist = false)
    private Course course;

    private String teacher;//授课教师

    //4-2表示星期四第二节课
    private String teachTime;//授课时间

    private String teachPlace;//授课地点

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    @TableField(exist = false)
    private Clazz clz;//一对一



}
