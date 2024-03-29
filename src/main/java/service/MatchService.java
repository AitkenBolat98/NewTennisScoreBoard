package service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;


@RequiredArgsConstructor
@Slf4j
public class MatchService extends Config{
    public CurrentMatches createMatch(Players player1,Players player2){
        CurrentMatches newMatch = CurrentMatches.builder()
                .player1(player1)
                .player2(player2)
                .build();
        Configuration configurationMatch = getConfiguration();
        SessionFactory sessionFactory = configurationMatch.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(newMatch);
            session.getTransaction().commit();
        }catch (Exception e){
            log.error(e.getMessage(),"match creation exception");
        }finally {
            session.close();
        }
        return newMatch;
    }
    public CurrentMatches getMatchById(Integer id){
        Configuration configuration = getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        CurrentMatches nullMatch = null;
        try {
            session.beginTransaction();
            String hql = "SELECT m FROM current_matches m WHERE m.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            CurrentMatches searchedMatch = (CurrentMatches) query.uniqueResult();
            session.getTransaction().commit();
            return searchedMatch;
        }catch (Exception e){
            log.error(e.getMessage(),"Find match by id exception");
        }finally {
            session.close();
        }
        return nullMatch;
    }

    public CurrentMatches getMatchByPlayerName(String name){
        Configuration configuration = getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        CurrentMatches nullMatch = null;
        try {
            session.beginTransaction();
            String hql = "SELECT\n" +
                    "    cm\n" +
                    "FROM\n" +
                    "    current_matches cm\n" +
                    "        JOIN\n" +
                    "    players p1 ON cm.player1 = p1\n" +
                    "        JOIN\n" +
                    "    players p2 ON cm.player2 = p2\n" +
                    "WHERE\n" +
                    "        p1.name =:name OR p2.name =:name";
            Query query = session.createQuery(hql);
            query.setParameter("name",name);
            CurrentMatches searchedMatch = (CurrentMatches) query.uniqueResult();
            session.getTransaction().commit();
            return searchedMatch;
        }catch (Exception e){
            log.error(e.getMessage(),"match by player exception");
        }finally {
            session.close();
        }
        return nullMatch;
    }

}
