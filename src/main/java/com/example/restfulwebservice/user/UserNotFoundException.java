package com.example.restfulwebservice.user;

// HTTP Status code
// 200번대 스테터스 -> OK
// 400번대 스테터스 -> Client측 오류
// 500번대 스테터스 -> Server측 오류
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 실행 시 발생하는 오류
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
