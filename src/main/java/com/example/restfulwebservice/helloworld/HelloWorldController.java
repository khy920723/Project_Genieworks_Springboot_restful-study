package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    
    // 어노테이션을 통한 주입 방법
    @Autowired
    private MessageSource messageSource;
    
    // GET
    // /hello-world (uri, endpoint)
    // path: 속성값
    // 기존 방식은 @RequestMapping(method=RequestMethod.GET, path="/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    // 반환값: HelloWorldBean
    // 빠른단축키: 알트 + 엔터
    // 반환 타입이 java.bean 형태라면 스프링에서는 JSON 형태로 변환하여 반환 (XML로 반환을 원하면 해당 라이브러리를 추가)
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        // 인스턴스로 반환
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    // 다른 변수명으로 사용 시 @PathVariable(value="name")으로 지정
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        // %s: 가변데이터가 올 수 있는 값
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    // 디폴트로 한국어 출력
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message", null, locale);
    }

}
