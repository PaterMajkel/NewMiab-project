package app.pb.DTO;

import app.pb.entity.UserGroup;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GroupDto {
    private Long id;
    private String name;
    private List<UserDto> users;

    public GroupDto(UserGroup group) {
        this.id = group.getId();
        this.name = group.getName();
        this.users = group.getUsers()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}