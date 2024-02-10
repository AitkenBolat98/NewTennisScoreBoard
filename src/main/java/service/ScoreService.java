package service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.CurrentMatches;
import module.Players;
import module.Score;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .setNumber(0)
                .pointWon(0)
                .build();
        Score scorePlayer2 = Score
                .builder()
                .player(player2)
                .match(newMatch)
                .gamesWon(0)
                .setNumber(0)
                .pointWon(0)
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
    public CurrentMatches updateMatchScore(Integer id,Integer playerScored){
        CurrentMatches match = matchService.getMatchById(id);
        Score newScore;
        if (playerScored == 1){
            newScore = calculateScore(match,match.getPlayer1());
        }else{
            newScore = calculateScore(match,match.getPlayer2());
        }

        Configuration configurationMatch = getConfiguration();
        SessionFactory sessionFactory = configurationMatch.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(newScore);
            session.getTransaction().commit();
        }catch (Exception e){
            log.error(e.getMessage(),"score update  exception");
        }finally {
            session.close();
        }
        return match;
    }
    public HashMap<String,List<Integer>> getSetGamePoint(Integer id){
        CurrentMatches match = matchService.getMatchById(id);
        HashMap<String,List<Integer>> map = new HashMap<>();
        Integer player1Set = match.getPlayer1().getScore().getSetNumber();
        Integer player1Game = match.getPlayer1().getScore().getGamesWon();
        Integer player1Point = match.getPlayer1().getScore().getPointWon();

        List<Integer> player1List = new ArrayList<>();
        player1List.add(player1Set);
        player1List.add(player1Game);
        player1List.add(player1Point);

        map.put("player1",player1List);

        Integer player2Set = match.getPlayer2().getScore().getSetNumber();
        Integer player2Game = match.getPlayer2().getScore().getGamesWon();
        Integer player2Point = match.getPlayer2().getScore().getPointWon();

        List<Integer> player2List = new ArrayList<>();
        player2List.add(player2Set);
        player2List.add(player2Game);
        player2List.add(player2Point);

        map.put("player2",player2List);

        return map;
    }
    private Score calculateScore(CurrentMatches match,Players player){
        if(isPlayerWonMatch(player) == true){
            Score winScore = null;
            return winScore;
        }
        Integer point = player.getScore().getPointWon();
        Integer game = player.getScore().getGamesWon();
        Integer set = player.getScore().getSetNumber();
        if(game == 6){
            game = 0;
            point = 0;
            set = winningSet(player);
        }
        if(point == 60){
            point = 0;
            game = winningGame(player);
        }else {
            point = winningPoint(player);
        }
        Score newScore = Score
                .builder()
                .id(player.getScore().getId())
                .match(match)
                .player(player)
                .pointWon(point)
                .gamesWon(game)
                .setNumber(set)
                .build();
        return newScore;
    }
    private Integer winningGame(Players player){
        Integer game = player.getScore().getGamesWon();

        return game+1;
    }
    private void updateSet(CurrentMatches match){

    }
    private Integer winningPoint(Players player){
        Integer point = player.getScore().getPointWon();
        return point+15;
    }
    private Integer winningSet(Players player){
        Integer set = player.getScore().getSetNumber();
        return set+1;
    }
    private boolean isPlayerWonMatch(Players player){
        Integer set = player.getScore().getSetNumber();
        Integer game = player.getScore().getGamesWon();
        if(set >= 2 && game >= 6){
            return true;
        }
        return false;
    }
}
