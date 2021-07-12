package com.ling.stums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author Lingkongran
 * @Date 2020/11/27 0027 20:40
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer cid;//班级id

    private String major;//班级对应的专业

    private String clazzName;

    private Integer population;//班级人数

   @JsonFormat(pattern ="yyyy" )
    //为方便 将Date改为String
    private String startYears;//入学年份


    private String instructorName;//辅导员姓名

//    //防止双向关联出现死循环导致栈溢出，首先这不是jpa的问题，是序列化的问题，我们只要中断序列化就可以了
//    @JsonIgnoreProperties(value = {"Clazz"})
//    @OneToMany(mappedBy = "clz",fetch = FetchType.LAZY)//抓取策略：默认为懒加载
//    @TableField(exist = false)
//    private List<Student> students;//一对多
}
