package module;


import lombok.*;

import javax.persistence.*;

@Entity(name = "current_matches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentMatches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne()
    @JoinColumn(name = "player1_id")
    private Players player1;
    @OneToOne()
    @JoinColumn(name = "player2_id")
    private Players player2;

    @OneToOne(mappedBy = "match",orphanRemoval = true)
    private Score score;
}
