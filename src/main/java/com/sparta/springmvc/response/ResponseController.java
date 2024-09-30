package com.sparta.springmvc.response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/response")
public class ResponseController {


    // ResponseBody : {"name":"Robbie", "age":95} , `Content-Type : text/html`
    // JSon 형태로 반환을 해야하는데 자바에선 JSon 을 읽지 못하니 String 에 담아서 반환한다.
    @GetMapping("/json/string")
    @ResponseBody
    public String helloStringJson() {
        return "{\"name\":\"Robbie\",\"age\":95}";
    }


    // `Content-Type : application/json` , ResponseBody : {"name":"Robbie", "age":95}
    // Map 자료구조처럼 반환. ket-value : name-"Robbie" , age-95
    @GetMapping("/json/class")
    @ResponseBody
    public Star helloClassJson () {
        return new Star("Robbie", 95);  //  Spring 에서 객체를 json 형태로 변환해주기 때문에 가능
    }


}