package com.springboot.first.service.impl;

import com.springboot.first.dao.SystemCfgDao;
import com.springboot.first.entity.SystemCfg;
import com.springboot.first.service.SystemCfgService;
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
