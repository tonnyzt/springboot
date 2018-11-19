package com.org.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统启动类
 *
 * @Author: Tonny
 * @CreateDate: 18/11/19 上午 11:33
 * @Version: 1.0
 */
@MapperScan("com.org.springboot.dao")
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
