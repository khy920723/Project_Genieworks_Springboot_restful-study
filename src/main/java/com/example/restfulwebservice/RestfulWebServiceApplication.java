package com.example.restfulwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
//@EnableWebMvc
public class RestfulWebServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestfulWebServiceApplication.class, args);
    }

    // @Bean: 개발자가 직접 제어가 불가능한 외부 라이브러리 등을 Bean으로 만들려 할 때 사용
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA); // 기본 한국어 처리
        return localeResolver;
    }
}
