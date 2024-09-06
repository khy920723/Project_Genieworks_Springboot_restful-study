package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

// 비즈니스 로직
// 어떤 역할을 하는 지 지정 필요(@Service 어노테이션, 어떤 역할을 하는 지 정확히 알 때)
@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usercCount = 3;

    // DB에 3명이라고 가정
    static {
        users.add(new User(1, "Kenneth", new Date(), "pass1", "707070-111111"));
        users.add(new User(2, "Alice", new Date(), "pass1", "707070-111111"));
        users.add(new User(3, "Elena", new Date(), "pass1", "707070-111111"));
    }

    public  List<User> findAll(){
        return users;
    }

    // 사용자 추가
    public User save(User user){
        // id가 존재하지 않는다면
        if(user.getId() == null){
            user.setId(++usercCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user: users){
            if(user.getId() == id){
                return user;
            }
        }
        // 일치하는 유저가 없을 시
        return null;
    }

    // 데이터를 찾아서 삭제
    public User deleteById(int id){
        // 열거형 데이터
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }

        // 데이터 없을 시
        return null;
    }
}
