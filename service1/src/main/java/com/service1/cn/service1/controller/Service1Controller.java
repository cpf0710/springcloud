package com.service1.cn.service1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/service1")
public class Service1Controller {
    @RequestMapping("/test1")
    public String test1() throws UnknownHostException {
        InetAddress ip4 = Inet4Address.getLocalHost();
        System.out.println(ip4.getHostAddress());
        return  ip4.getHostAddress();
    }
}
