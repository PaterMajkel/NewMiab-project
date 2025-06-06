package app.pb.service;

import app.pb.DTO.Relationship;
import app.pb.entity.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {
    Mono<User> createUser(User user);
    Mono<User> findUser(Long id);

    List<User> findAll();
    Mono<List<Relationship>> getUserRelationships(List<Long> ids);
}
