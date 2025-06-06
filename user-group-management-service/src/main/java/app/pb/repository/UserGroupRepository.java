package app.pb.repository;

import app.pb.entity.UserGroup;
import app.pb.entity.UserGroup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    @EntityGraph(attributePaths = "users")
    UserGroup findByName(String name);
}
