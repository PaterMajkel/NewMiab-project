package app.pb.service;

import app.pb.DTO.Relationship;
import app.pb.entity.User;
import app.pb.repository.UserRepository;
import app.pb.entity.User;
import app.pb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final WebClient webClient;
    @Autowired
    public UserServiceImpl(WebClient webClient, UserRepository userRepository) {
        this.webClient = webClient;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> createUser(User user) {
        return Mono.just(userRepository.save(user));
    }

    @Override
    public Mono<User> findUser(Long id) {
        return Mono.justOrEmpty(userRepository.findById(id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<List<Relationship>> getUserRelationships(List<Long> ids) {
        String joinedIds = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/relationships/filter-by-ids")
                        .queryParam("ids", joinedIds)
                        .build())
                .retrieve()
                .bodyToFlux(Relationship.class)
                .collectList();
    }
}
