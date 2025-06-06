package app.pb.service;

import app.pb.entity.UserGroup;
import reactor.core.publisher.Mono;

public interface GroupService {
    Mono<UserGroup> createGroup(UserGroup group);
    Mono<UserGroup> findGroup(Long id);
    UserGroup addUserToGroup(Long groupId, Long userId);
    UserGroup removeUserFromGroup(Long groupId, Long userId);
}
