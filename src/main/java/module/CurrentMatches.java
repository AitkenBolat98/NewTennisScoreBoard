package module;


import lombok.*;

import javax.persistence.*;

@Entity(name = "matches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    private Players player1;
    @OneToOne(fetch =  FetchType.LAZY)
    private Players player2;
    @OneToOne(fetch = FetchType.LAZY)
    private Players winner;
}
