package module;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "players")
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

    @OneToOne(mappedBy = "player1")
    private CurrentMatches player1Matches;

    @OneToOne(mappedBy = "player2")
    private CurrentMatches player2Matches;

}
