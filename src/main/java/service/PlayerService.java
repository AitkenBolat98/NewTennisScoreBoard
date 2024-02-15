package service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.CurrentMatches;
import module.Players;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Slf4j
public class PlayerService extends Config  {
    private MatchService currentMatchService = new MatchService();
    private ScoreService scoreService = new ScoreService(currentMatchService);
    public void createPlayers(String player1Name, String player2Name){
        Players player1 = Players.builder()
                .name(player1Name)
                .build();

        Players player2 = Players.builder()
                .name(player2Name)
                .build();

        Configuration configurationPlayer = getConfiguration();
        SessionFactory sessionFactory = configurationPlayer.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            session.beginTransaction();

            session.save(player1);
            session.save(player2);
            session.getTransaction().commit();
            CurrentMatches match = currentMatchService.createMatch(player1,player2);
            scoreService.createScore(match,player1,player2);
        }catch (Exception e){
            log.error("exception occured",e);
            throw e;
        }finally {
            session.close();
        }
    }
    public boolean isPlayerExist(String name){
        Players player = getPlayerByName(name);
        if(player == null){
            return false;
        }
        return true;
    }
    public Players getPlayerByName(String name){
        Configuration configuration = getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Players nullPlayer = null;
        try {
            session.beginTransaction();
            String hql = "SELECT p FROM players p WHERE p.name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name",name);
            Players searchedPlayer = (Players) query.uniqueResult();
            session.getTransaction().commit();

            return searchedPlayer;
        }catch (Exception e){
            log.error(e.getMessage(),"Player search exception");
        }finally {
            session.close();
        }
        return nullPlayer;
    }

  /*  public String getPlayerByID(Integer id){
        Configuration configuration = getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        String playerName = null;
        try {
            session.beginTransaction();
            Players searchedPlayer = session.get(Players.class,id);
            session.getTransaction().commit();
            return searchedPlayer.getName();
        }catch (Exception e){
            log.error(e.getMessage(),"");
        }
    }*/

}
