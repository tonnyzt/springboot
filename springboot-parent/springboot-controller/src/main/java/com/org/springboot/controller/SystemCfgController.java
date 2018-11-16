package com.org.springboot.controller;

import com.org.springboot.entity.SystemCfg;
import com.org.springboot.service.SystemCfgService;
import com.org.springboot.service.init.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "config")
@Controller
public class SystemCfgController {

    @Autowired
    private SystemCfgService service;

    @Autowired
    private InitService initService;

    @RequestMapping(value = "cache/getAll", method = RequestMethod.GET)
    @ResponseBody
    List<SystemCfg> index() {
        return service.getAll();
    }


    @RequestMapping(value = "cache/get", method = RequestMethod.GET)
    @ResponseBody
    String index2() {
        return initService.getLocalCache(InitService.NO_FLUSH, InitService.system, "robot.operator.call.days");
    }
}
