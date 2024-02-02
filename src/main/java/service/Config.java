package servlets;

import module.Matches;
import module.Players;
import org.hibernate.cfg.Configuration;

public class Config {

    public Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Matches.class);
        configuration.addAnnotatedClass(Players.class);
        configuration.configure();
        return configuration;
    }
}
