package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok 디펜던시(@Data)를 사용하면 자동으로 생성자, getter setter등을 생성해줌
// Structure 탭에서 관련한 멤버변수와 멤버메서드들 확인 가능
@Data
// 모든 arguments가 갖고 있는 생성자 생성
// message 값을 사용할 수 있는 생성자 생성
@AllArgsConstructor
// default 생성자 생성
@NoArgsConstructor
public class HelloWorldBean {
    // property만 추가해도 lombok dependency library 덕에 세팅 가능
    private String message;

}
