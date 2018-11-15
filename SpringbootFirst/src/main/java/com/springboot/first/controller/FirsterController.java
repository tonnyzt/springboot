package com.springboot.first.controller;

import com.springboot.first.dao.SystemCfgDao;
import com.springboot.first.entity.SystemCfg;
import com.springboot.first.service.SystemCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "config")
@Controller
public class FirsterController {

    @Autowired
    private SystemCfgService service;

    @RequestMapping(value = "cache/getAll", method = RequestMethod.GET)
    @ResponseBody
    List<SystemCfg> index() {
        System.out.println("123");
        return service.getAll();
    }
}
