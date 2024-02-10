package module;

import lombok.*;

import javax.persistence.*;

@Entity(name = "score")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "player_id")
    private Players player;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "match_id")
    private CurrentMatches match;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "games_won")
    private Integer gamesWon;
    @Column(name ="point_won")
    private Integer pointWon;

}
