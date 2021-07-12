package com.ling.stums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;

/**
 * @Author Lingkongran
 * @Date 2020/11/27 0027 20:35
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer sno;//学号

    private String sname;//学生姓名

    private Boolean sex;//性别

    private Date birthday;//生日

    private String major;//专业

    private String homeAddress;//家庭住址

    @Column(length = 11)
    private String phone;//联系电话

    private String note;//备注


    /*
     允许所有级联操作：cascade = CascadeType.ALL
    *多对一中一的一方默认加载为立即加载：fetch = FetchType.EAGER
     */

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")//外键
    //@TableField(exist = false)
    private Clazz clz;//多对一

}
