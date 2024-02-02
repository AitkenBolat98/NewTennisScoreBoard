package service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.Matches;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService{
    private final Config config;
    @Override
    public void updateScore(HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getParameter("uuid");
        try {
            SessionFactory sessionFactory = config.getConfiguration().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Matches match = session.get(Matches.class,uuid);


        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
