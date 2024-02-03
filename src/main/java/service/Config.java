package service;

import module.*;
import org.hibernate.cfg.Configuration;

public class Config {

    public Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(CurrentMatches.class);
        configuration.addAnnotatedClass(Players.class);
        configuration.configure();
        return configuration;
    }
}
