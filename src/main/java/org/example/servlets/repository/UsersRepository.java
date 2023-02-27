package org.example.servlets.repository;

import org.example.servlets.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersRepository {
    private static UsersRepository instance = null;

    public static UsersRepository getInstance() {
        if (instance == null) {
            instance = new UsersRepository();

            Collections.addAll(instance.users,
                    new User(1, "Vasya228", 1500),
                    new User(2, "Vadim339", 4200),
                    new User(3, "Petya1337", 7500)
            );
        }
        return instance;
    }

    private List<User> users;
    private int globalId;

    private UsersRepository() {
        users = new ArrayList<>();
        globalId = 0;
    }

    public void addNew(User user) {
        globalId++;
        user.setId(globalId);

        users.add(user);
    }

    public List<User> getAll() {
        return users;
    }
}
