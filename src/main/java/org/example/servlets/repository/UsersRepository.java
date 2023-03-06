package org.example.servlets.repository;

import org.example.servlets.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersRepository {
    private SessionFactory sessionFactory;

    public UsersRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addNew(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);

        transaction.commit();
        session.close();
    }

    public List<User> getAll() throws ServletException {
        return (List<User>) sessionFactory.openSession().createQuery("FROM User ORDER BY id").list();
    }

}
