package app.pb.controller;

import app.pb.DTO.GroupDto;
import app.pb.Tools.FamilyTreeDrawerASCII;
import app.pb.entity.User;
import app.pb.service.GroupService;
import app.pb.service.UserService;
import app.pb.entity.UserGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;

    @PostMapping
    public Mono<UserGroup> createGroup(@RequestBody UserGroup group) {
        return groupService.createGroup(group);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<GroupDto>> getGroup(@PathVariable Long id) {
        return groupService.findGroup(id)
                .map(group -> ResponseEntity.ok(new GroupDto(group)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{groupId}/users/{userId}")
    public Mono<ResponseEntity<GroupDto>> addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        return Mono.fromCallable(() -> groupService.addUserToGroup(groupId, userId))
                .map(group -> ResponseEntity.ok(new GroupDto(group)))
                .doOnError(e -> System.err.println("Error adding user to group: " + e.getMessage()))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public Mono<ResponseEntity<GroupDto>> removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        return Mono.fromCallable(() -> groupService.removeUserFromGroup(groupId, userId))
                .map(group -> ResponseEntity.ok(new GroupDto(group)))
                .onErrorResume(e -> {
                    e.printStackTrace(); // lub log.error(...)

                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }

    @GetMapping("/DrawGroup/{groupId}")
    public Mono<ResponseEntity<String>> drawGroup(@PathVariable Long groupId){
        return groupService.findGroup(groupId)
                .flatMap(group -> {
                    Set<User> users = group.getUsers();
                    List<Long> userIds = users.stream().map(User::getId).toList();

                    var userList = new ArrayList<User>(users);
                    return userService.getUserRelationships(userIds)
                            .map(relationships -> {
                                String tree = FamilyTreeDrawerASCII.drawAsciiFamilyTree(userList, relationships);
                                return ResponseEntity.ok(tree);
                            });
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }
}
