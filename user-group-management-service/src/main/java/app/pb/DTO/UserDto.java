package app.pb.DTO;

import app.pb.entity.User;

public class UserDto {
    private Long id;
    private String username;
    private String email;

    // Constructor
    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
