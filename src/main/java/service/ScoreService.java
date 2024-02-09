package service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.CurrentMatches;
import module.Players;
import module.Score;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@Slf4j
@RequiredArgsConstructor
public class ScoreService extends Config {
    private final MatchService matchService;
    public void createScore(CurrentMatches newMatch, Players player1, Players player2){
        Score scorePlayer1 = Score
                .builder()
                .player(player1)
                .match(newMatch)
                .gamesWon(0)
                .setNumber(1)
                .build();
        Score scorePlayer2 = Score
                .builder()
                .player(player2)
                .match(newMatch)
                .gamesWon(0)
                .setNumber(0)
                .build();
        Configuration configurationMatch = getConfiguration();
        SessionFactory sessionFactory = configurationMatch.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(scorePlayer1);
            session.save(scorePlayer2);
            session.getTransaction().commit();
        }catch (Exception e){
            log.error(e.getMessage(),"score creation exception");
        }finally {
            session.close();
        }
    }
    public void updateMatchScore(Integer id,Integer playerScored){
        CurrentMatches match = matchService.getMatchById(id);
        Score newScore;
        if (playerScored == 1){
             newScore = Score
                    .builder()
                    .player(match.getPlayer1())
                    .match(match)
                    .gamesWon(match.getScore().getGamesWon() +15)
                    .setNumber(match.getScore().getSetNumber()+1)
                    .build();
        }else{
            newScore = Score
                    .builder()
                    .player(match.getPlayer2())
                    .match(match)
                    .gamesWon(match.getScore().getGamesWon() +15)
                    .setNumber(match.getScore().getSetNumber()+1)
                    .build();
        }
        Configuration configurationMatch = getConfiguration();
        SessionFactory sessionFactory = configurationMatch.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(newScore);
            session.getTransaction().commit();
        }catch (Exception e){
            log.error(e.getMessage(),"score update  exception");
        }finally {
            session.close();
        }

    }

}
