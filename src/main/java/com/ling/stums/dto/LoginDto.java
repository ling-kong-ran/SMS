package com.ling.stums.dto;

import lombok.*;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 13:13
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    private Integer uid;//用户id

    private String username;//用户名

    private String roles;//用户角色类别

}
