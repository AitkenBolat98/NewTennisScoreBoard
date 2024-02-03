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
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "")
    private List<FinishedMatches> finishedMatches;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<CurrentMatches> currentMatches;


}
