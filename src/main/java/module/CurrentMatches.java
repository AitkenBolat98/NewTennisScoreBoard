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
    @JoinColumn(name = )
    private Players player1;
    @OneToOne()
    private Players player2;

}
