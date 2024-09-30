package com.sparta.springmvc.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    private static long visitCount =0;

    // static 폴더 내 html 에 접근하는 방법
    // 1. url 경로에서 직접 접근 : http://localhost:8080/hello.html
    // 2. 템플릿 엔진, dependencies 에 thymeleaf 를 끄고, 컨트롤러를 거친다 : http://localhost:8080/static-hello
    @GetMapping("/static-hello")
    public String hello() {
        return "hello.html";
    }

    // 3. 템플릿 엔진, dependencies 에 thymeleaf 에 킨 상태에서 반환 : http://localhost:8080/html/redirect
    // /html/redirect 를 /hello.html 로 재설정
    @GetMapping("/html/redirect")
    public String htmlStatic () {
        return "redirect:/hello.html";
    }

    // templates 폴더 내 정적 페이지 html 접근
    // return 값으로 "hello" , "hello.html" 둘 다 가능
    @GetMapping("/html/templates")
    public String htmlTemplates () {
        return "hello";
    }

    // templates 폴더 내 동적 페이지 접근
    @GetMapping("/html/dynamic")
    public String htmlDynamic(Model model) {  // frontController 에서 model 을 넣어줌
        visitCount ++;
        model.addAttribute("visits", visitCount);  //
        return "hello-visit";
    }

}

//spring.thymeleaf.prefix=classpath:/templates/
//
//spring.thymeleaf.cache=false
//spring.thymeleaf.check-template-location=true