package com.org.springboot.entity;

import lombok.Data;


/**
 * 系统配置类
 *
 * @Author: Tonny
 * @CreateDate: 18/11/19 上午 11:32
 * @Version: 1.0
 */
@Data
public class SystemCfg {
    private String key;
    private String value;
    private String remark;
    protected Long id;// ID
    protected Long addTime;// 创建日期
    protected Long addUser;// 创建人
    protected Long updateTime;// 修改日期
    protected Long updateUser;// 修改人
}
