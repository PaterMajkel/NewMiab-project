package app.pb.controller;

import app.pb.DTO.UserDto;
import app.pb.entity.User;
import app.pb.service.UserService;
import app.pb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<ResponseEntity<UserDto>> createUser(@RequestBody User user) {
        return userService.createUser(user).map(usr -> ResponseEntity.ok(new UserDto(usr)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> getUser(@PathVariable Long id) {
        return userService.findUser(id)
                .map(user -> ResponseEntity.ok(new UserDto(user)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<UserDto> all() {
        return userService.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
