package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

// lombok
@Data
@AllArgsConstructor
@NoArgsConstructor // V2에서 상속받기 위해
//@JsonFilter("UserInfo") // 필터의 이름 지정 가능
public class User {
    private Integer id;

    // 제약조건
    // 최소 2글자 이상
    @Size(min = 2, message = "Name은 두 글자 이상 입력해주세요.")
    private String name;

    // 제약조건
    @Past
    private Date joinDate;

    private String password;
    private String ssn;
}
