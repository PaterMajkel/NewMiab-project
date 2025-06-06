package app.pb.service;

import app.pb.entity.User;
import app.pb.entity.UserGroup;
import app.pb.repository.UserGroupRepository;
import app.pb.entity.UserGroup;
import app.pb.repository.UserGroupRepository;
import app.pb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final UserGroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    public Mono<UserGroup> createGroup(UserGroup group) {
        return Mono.just(groupRepository.save(group));
    }

    @Override
    public Mono<UserGroup> findGroup(Long id) {
        return Mono.justOrEmpty(groupRepository.findByIdWithUsers(id));
    }
    @Override
    @Transactional
    public UserGroup addUserToGroup(Long groupId, Long userId) {
        UserGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("Adding user " + user.getUsername() + " to group " + group.getName());
        group.getUsers().size();

        group.getUsers().add(user);
        UserGroup saved = groupRepository.save(group);
        System.out.println("Group saved with users count: " + saved.getUsers().size());
        return saved;
    }

    @Override
    public UserGroup removeUserFromGroup(Long groupId, Long userId) {
        UserGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean removed = group.getUsers().remove(user);
        if (!removed) {
            throw new RuntimeException("User not in group");
        }

        return groupRepository.save(group);
    }


}
