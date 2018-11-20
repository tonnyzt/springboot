package com.org.springboot.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 系统配置类
 *
 * @Author: Tonny
 * @CreateDate: 18/11/19 上午 11:32
 * @Version: 1.0
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
}
