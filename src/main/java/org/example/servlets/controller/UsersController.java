package org.example.servlets.controller;

import com.google.gson.Gson;
import org.example.servlets.dbconnection.HibernateSession;
import org.example.servlets.model.User;
import org.example.servlets.repository.UsersRepository;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {
    private UsersRepository usersRepository;
    private Gson gson;

    public UsersController() {
        SessionFactory sessionFactory = HibernateSession.getInstance().getSessionFactory();
        usersRepository = new UsersRepository(sessionFactory);

        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = usersRepository.getAll();
        String usersAsJson = gson.toJson(users);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(usersAsJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();

        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }

        String json = buffer.toString();

        User user = gson.fromJson(json, User.class);
        usersRepository.addNew(user);
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
