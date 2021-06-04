package com.service1.cn.service1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class Service1Controller {
    @RequestMapping("/test1")
    public String test1(){
        return  "222222222222222222222222";
    }
}
