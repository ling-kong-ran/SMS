package com.ling.stums.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 10:52
 * @Version 1.0
 */
@Configuration
@MapperScan("com.ling.stums.mapper")
public class MybatisPlusConfig {
}
