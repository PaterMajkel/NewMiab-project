package app.relationship.controller;

import app.relationship.entity.Relationship;
import app.relationship.service.RelationshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relationships")
public class RelationshipController {

    private final RelationshipService service;

    public RelationshipController(RelationshipService service) {
        this.service = service;
    }

    @GetMapping
    public List<Relationship> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Relationship create(@RequestBody Relationship relationship) {
        return service.save(relationship);
    }

    @GetMapping
    public List<Relationship> getWhereUserInList(@RequestParam List<Long> userIds) {
        return service.getWhere(userIds);
    }
}
