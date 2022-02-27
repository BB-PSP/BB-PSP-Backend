package bbpsp.backend.domain.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "player_unique")
public class PlayerUnique {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_unique_id")
    private Long id;

    @Column(name = "player_unique_name")
    private String name;

    public static PlayerUnique createEntity(String name) {
        return new PlayerUnique(name);
    }

    private PlayerUnique(String name) {
        this.name = name;
    }
}
