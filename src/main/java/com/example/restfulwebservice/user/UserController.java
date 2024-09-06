package com.example.restfulwebservice.user;

import lombok.Data;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// 레스트 컨트롤러 역할
// 의존성 주입으로 활용
@RestController
@Data
public class UserController {
    private UserDaoService service;

    // 생성자를 통한 의존성 주입
    public UserController(UserDaoService service) {
        this.service = service;
    }

    // 전체 사용자 목록 조회
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        // 컨트롤 + 마우스 클릭으로 해당 작성된 클래스 이동가능
        return service.findAll();
    }

    // 상세 조회
    // GET /users/1 or /users/10 -> 서버에서는 String 형태로 전달
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {

        User user = service.findOne(id);
        // 예외처리
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // HATEOAS
        EntityModel<User> model = EntityModel.of(user); // EntityModel의 생성자는 protected로 되어있어 바로 사용 불가능 (수동으로 Entity를 적용할 시 Entity<T> 클래스를 이용하고 static method로 객체를 만들기 때문에 Entity.of()를 통해 메서드를 생성함)
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers()); // 어떤 링크를 추가할 것인 지 지정 (linkTo 메서드는 static 메서드)

        model.add(linkTo.withRel("all-users")); // url값 연결 (retrieveAllUsers와 "all-users" 하이퍼링크 연결)

        return model;
    }

    // post 요청이 들어왔을 때 uri 값이 일치하면 실행
    // get의 메서드와 이름은 같지만 post기 때문에 다른 메서드로 취급
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        // 현재 요청되어진 리퀘스트
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                // 가변 변수에 새로운 아이디값 추가
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 사용자 삭제 메서드
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
