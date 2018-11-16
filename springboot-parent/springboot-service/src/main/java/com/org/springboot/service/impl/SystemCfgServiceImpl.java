package com.org.springboot.service.impl;


import com.org.springboot.dao.SystemCfgDao;
import com.org.springboot.entity.SystemCfg;
import com.org.springboot.service.SystemCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemCfgServiceImpl implements SystemCfgService {

    @Autowired
    private SystemCfgDao systemCfgDao;

    @Override
    public List<SystemCfg> getAll() {
        return systemCfgDao.getAll();
    }
}
