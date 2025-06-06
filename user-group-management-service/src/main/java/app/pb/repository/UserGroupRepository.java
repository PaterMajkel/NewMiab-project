package app.pb.repository;

import app.pb.entity.UserGroup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    @EntityGraph(attributePaths = "users")
    Optional<UserGroup> findById(Long ID);

    @Query("SELECT g FROM UserGroup g LEFT JOIN FETCH g.users WHERE g.id = :id")
    Optional<UserGroup> findByIdWithUsers(@Param("id") Long id);

}
