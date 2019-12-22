package com.fatcao.my.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: FatCao
 * @Date: 2019-12-21 22:44
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String sayHi(){
        return "Hello Spring Boot";
    }
}
