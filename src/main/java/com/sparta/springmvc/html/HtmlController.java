package com.sparta.springmvc.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.Thymeleaf;

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

/*
 동적 페이지 처리 과정
 1. Client 의 요청을 **Controller**에서 **Model** 로 처리합니다.
    1. DB 조회가 필요하다면 DB 작업 후 처리한 데이터를 **Model**에 저장합니다.
 2. Template engine(Thymeleaf) 에게 **View**, **Model** 전달합니다.
    1. **View**: 동적 HTML 파일
    2. **Model**: View 에 적용할 정보들
 3. Template engine
    1. **View**에 **Model**을 적용 → 동적 웹페이지 생성
        1. 예) 로그인 성공 시, "로그인된 사용자의 Nickname"을 페이지에 추가
        2. Template engine 종류: 타임리프(Thymeleaf), Groovy, FreeMarker, Jade, JSP 등
 4. Client(브라우저)에게 **View**(동적 웹 페이지, HTML)를 전달 해줍니다.*/
