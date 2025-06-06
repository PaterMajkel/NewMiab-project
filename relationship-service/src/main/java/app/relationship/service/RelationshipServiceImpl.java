package app.relationship.service;

import app.relationship.entity.Relationship;
import app.relationship.repository.RelationshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    private final RelationshipRepository repository;

    public RelationshipServiceImpl(RelationshipRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Relationship> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Relationship> getWhere(List<Long> userIds) {
        return repository.getRelationshipByIdIsIn(userIds);
    }

    @Override
    public Relationship save(Relationship r) {
        return repository.save(r);
    }
}
