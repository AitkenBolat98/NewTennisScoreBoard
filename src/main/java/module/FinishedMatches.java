package module;

import lombok.*;

import javax.persistence.*;

@Entity(name = "finished_matches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinishedMatches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    private Players player1;
    @OneToOne(fetch = FetchType.EAGER)
    private Players player2;
    @OneToOne(fetch = FetchType.EAGER)
    private Players winner;
}
