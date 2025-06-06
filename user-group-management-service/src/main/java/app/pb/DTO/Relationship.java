package app.pb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generuje gettery, settery, toString, equals, hashCode
@NoArgsConstructor // Generuje pusty konstruktor
@AllArgsConstructor // Generuje konstruktor ze wszystkimi polami
public class Relationship {

    private Long id;

    private Long parentId;
    private Long childId;

    private String type;
}
