package com.org.springboot.entity;

import lombok.Data;
import lombok.ToString;


/**
 * Created by robot on 2016/4/19 0019.
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
