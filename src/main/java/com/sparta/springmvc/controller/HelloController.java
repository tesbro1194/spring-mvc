package com.sparta.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api")  // /api 로 시작되는 요청은 아래 클래스로 오고 그 이후 상세한 path 를 분석하여 Mapping 함.
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody                   //  resources 에서 찾지 않고 아래 {} 대로 처리.
    public String hello() {
        return "Hello Again!!!!!";
    }

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return "Get Method 요청";
    }


    @PostMapping("/post")  // 경로는 중복될 수 있음. 즉 "/api/hello" 가능. 하지만 Post 라는 메서드와 Get 이라는 메서드는 달라야한다!
    @ResponseBody
    public String post() {
        return "Post Method 요청";
    }

    @PutMapping("/put")
    @ResponseBody
    public String put() {
        return "Put Method 요청";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete () {
        return "Delete Method 요청";
    }
}
