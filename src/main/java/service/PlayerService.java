package service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.Players;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    private final Config configuraiton;
    @Override
    public void createPlayers(String player1Name, String player2Name){
        Configuration configurationPlayer = configuraiton.getConfiguration();
        Players player1 = Players.builder()
                .name(player1Name)
                .build();
        Players player2 = Players.builder()
                .name(player2Name)
                .build();
        try {
            SessionFactory sessionFactory = configurationPlayer.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(player1);
            session.save(player2);
            session.getTransaction().commit();

        }catch (Exception e){
            log.error("exception occured",e);
            throw e;
        }
    }
}
