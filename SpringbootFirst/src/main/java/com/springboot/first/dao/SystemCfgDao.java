package com.springboot.first.dao;


import com.springboot.first.entity.SystemCfg;

import java.util.List;

public interface SystemCfgDao {
    void setSysteCfg(SystemCfg systeCfg);

    List<SystemCfg> getAll();

    SystemCfg getOne(SystemCfg systemCfgFind);
}