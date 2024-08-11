package com.myy.d5_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: helloController
 * Package: com.example.demo_d5.controller
 * Description:
 *
 * @Author: miaoyy
 * @Create: 2024-08-11 - 2:20
 */
@RestController
public class helloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello,world!";
    }
}
