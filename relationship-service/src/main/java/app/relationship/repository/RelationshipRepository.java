package app.relationship.repository;

import app.relationship.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    @Query("SELECT u FROM Relationship u WHERE u.parentId IN :ids or u.childId in :ids")
    List<Relationship> getRelationshipByIdIsIn(Collection<Long> ids);
}
