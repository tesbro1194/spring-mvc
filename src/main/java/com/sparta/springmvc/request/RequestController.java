package com.sparta.springmvc.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class RequestController {
    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

/*      Client 즉, 브라우저에서 서버로 HTTP 요청을 보낼 때 데이터를 함께 보낼 수 있다. 이때 사용하는 방식들을 알아보자.
     Path Variable 방식 : 서버에 보내려는 데이터를 URL 경로에 추가
     [Request sample] GET http://localhost:8080/hello/request/star/Robbie/age/95  `/Robbie` 와 `/95` 부분을 받는다.*/
    @GetMapping("/star/{name}/age/{age}")  // URL 경로에서 데이터를 받고자 하는 위치의 경로에 {data} 중괄호를 사용
    @ResponseBody
    //해당 요청 메서드 파라미터에 @PathVariable 애너테이션과 함께 {name} 중괄호에 선언한 변수명과 변수타입을 선언하면 해당 경로의 데이터를 받아올 수 있다.
    public String helloRequestPath(@PathVariable String name, @PathVariable(required = false) int age)
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }

    // Request Param 방식 : 서버에 보내려는 데이터를 URL 경로 마지막에 ? 와 & 를 사용하여 추가
    // [Request sample] GET http://localhost:8080/hello/request/form/param?name=Robbie&age=95   param 이후 ? , 추가, 데이터 구분은 &
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }
    // @PathVariable, @RequestParam 생략 가능, simple value 타입일 때
    // (required = false) 클라이언트가 보내는 age 가 없어도 받을 수 있음. 초기값이 true 로 들어감.


/*  클라이언트에서 서버로, Post 방식으로 넘어오는 데이터를 받는 방법. payload 에 담겨서 넘어옴. form 태그!
    [Request sample] : POST http://localhost:8080/hello/request/form/param
    Header
     Content type: application/x-www-form-urlencoded
    Body
     name=Rob&age=30   query String 표현이다.                 */
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }




    // HTTP 데이터를 객체로 처리하는 방법 : Setter 메서드, 혹은 입력을 받을 수 있는 생성자 메서드가 필요함.

/*   1. [Request sample] : POST http://localhost:8080/hello/request/form/model
     Header
      Content type: application/x-www-form-urlencoded
     Body
      name=Robbie&age=95*/    // Body 에 query String 으로 넘어온 것을 객체로 ! @ModelAttribute
    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    //  2. [Request sample] : GET http://localhost:8080/hello/request/form/param/model?name=Robbie&age=95
    // `?name=Robbie&age=95` 이런 방식으로 넘어온 데이터를 객체로 ! @ModelAttribute
    @GetMapping("/form/param/model")
    @ResponseBody
    public String helloRequestParam(@ModelAttribute Star star) {  // @ModelAttribute 생략 가능 , 객체일 때
        return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
    }

/*   [Request sample] : POST http://localhost:8080/hello/request/form/json
     Header
      Content type: application/json
     Body
      {"name":"Robbie","age":"95"} */     //  body 에 Json 형식으로 넘어옴.
    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }
}