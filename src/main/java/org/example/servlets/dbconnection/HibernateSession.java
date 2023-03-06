package org.example.servlets.dbconnection;

import org.example.servlets.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static HibernateSession instance = null;

    private SessionFactory sessionFactory;

    private HibernateSession() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            System.out.println("HibernateSession init error:" + e);
        }
    }

    public static HibernateSession getInstance() {
        if (instance == null) {
            instance = new HibernateSession();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
