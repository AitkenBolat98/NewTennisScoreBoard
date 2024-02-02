package module;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Players")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  Players {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matches> matches;

}
