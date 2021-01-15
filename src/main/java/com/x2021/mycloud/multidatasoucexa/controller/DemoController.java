package com.x2021.mycloud.multidatasoucexa.controller;

import com.x2021.mycloud.multidatasoucexa.biz.LogService;
import com.x2021.mycloud.multidatasoucexa.entity.Log;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("log")
@RestController
public class DemoController {


    @Autowired
    private LogService logService;

    @GetMapping("queryOne")
    public Log getOne() {
        return logService.selectOne();
    }

    @GetMapping("queryTwo")
    public Log getTwo() {
        return logService.selectOne2();
    }


    @GetMapping("insert")
    public Long insert() {
        return logService.insert();
    }

    @GetMapping("insert2")
    public int insert2() {
        return logService.insert2();
    }

    @GetMapping("trans")
    public int trans() {
        return logService.transaction();
    }

    @GetMapping("listAll")
    public List<Log> getAll() {
        return logService.listAll();
    }

}
