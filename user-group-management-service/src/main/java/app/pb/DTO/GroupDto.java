package app.pb.DTO;

import app.pb.entity.UserGroup;
import lombok.Data;

@Data
public class GroupDto {
    private Long id;

    private String name;

    public GroupDto(UserGroup group) {
        this.id = group.getId();
        this.name = group.getName();
    }
}
