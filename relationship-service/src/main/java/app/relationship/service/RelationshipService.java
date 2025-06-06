package app.relationship.service;

import app.relationship.entity.Relationship;

import java.util.List;

public interface RelationshipService {
    List<Relationship> getAll();
    List<Relationship> getWhere(List<Long> userIds);
    Relationship save(Relationship r);
}
