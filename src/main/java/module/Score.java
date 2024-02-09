package module;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "score")
@Data
@Builder
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "player_id")
    private Players player;

    @OneToOne()
    @JoinColumn(name = "match_id")
    private CurrentMatches match;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "games_won")
    private Integer gamesWon;

}
