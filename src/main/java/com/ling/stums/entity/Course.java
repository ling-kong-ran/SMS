package com.ling.stums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @Author Lingkongran
 * @Date 2020/11/27 0027 23:34
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer cno;//课程号


    private String courseName;//课程名


    private Integer classHours;//学时


    private Integer credit;//学分

}
