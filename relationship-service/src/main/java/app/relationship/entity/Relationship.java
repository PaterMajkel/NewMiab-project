package app.relationship.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data // Generuje gettery, settery, toString, equals, hashCode
@NoArgsConstructor // Generuje pusty konstruktor
@AllArgsConstructor // Generuje konstruktor ze wszystkimi polami
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;
    private Long childId;

    @Enumerated(EnumType.STRING)
    private RelationshipType type;
}
