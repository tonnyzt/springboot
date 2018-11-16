package com.org.springboot.dao;


import com.org.springboot.entity.SystemCfg;

import java.util.List;

public interface SystemCfgDao {
    void setSysteCfg(SystemCfg systeCfg);

    List<SystemCfg> getAll();

    SystemCfg getOne(SystemCfg systemCfgFind);
}