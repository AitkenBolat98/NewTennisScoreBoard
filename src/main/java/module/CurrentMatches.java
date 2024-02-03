package module;


import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

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
    @OneToOne(fetch = FetchType.EAGER)
    private Players player1;
    @OneToOne(fetch = FetchType.EAGER)
    private Players player2;

}
