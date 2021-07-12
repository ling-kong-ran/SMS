package com.ling.stums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @Author Lingkongran
 * @Date 2020/11/27 0027 23:44
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer gid;//成绩id ,一张表可以没有主键，但最好确定每一列的唯一性，设置一个主键

    //private Integer sno;//学号
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "sno")//外键
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "cno")//外键
    private Course course;
   // private Integer cno;//课程号

    private Integer score;//分数


}
